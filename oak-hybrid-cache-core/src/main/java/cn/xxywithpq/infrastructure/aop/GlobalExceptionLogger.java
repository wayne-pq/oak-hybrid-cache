package cn.xxywithpq.infrastructure.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalExceptionLogger {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionLogger.class);

	// 定义切入点：拦截所有Controller层方法（可根据需要调整包路径）
	@Pointcut("execution(* com.example.yourpackage.controller..*.*(..))")
	public void controllerLayer() {
	}

	// 定义异常通知
	@AfterThrowing(pointcut = "controllerLayer()", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		// 获取异常详细信息
		String errorMsg = buildErrorMessage(joinPoint, ex);

		// 记录日志（可扩展为写入数据库/文件）
		logger.error("全局异常捕获 ===> {}", errorMsg);

		// 这里可以添加其他处理逻辑，比如发送报警等
	}

	private String buildErrorMessage(JoinPoint joinPoint, Throwable ex) {
		StringBuilder errorMsg = new StringBuilder();

		// 记录方法信息
		errorMsg.append("\nMethod: ").append(joinPoint.getSignature().toShortString());

		// 记录参数
		errorMsg.append("\nParams: ");
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			errorMsg.append("\n  arg[").append(i).append("]: ")
					.append(args[i] != null ? args[i].toString() : "null");
		}

		// 记录异常信息
		errorMsg.append("\nException: ").append(ex.getClass().getName())
				.append("\nMessage: ").append(ex.getMessage())
				.append("\nStack Trace: ").append(getStackTrace(ex));

		return errorMsg.toString();
	}

	private String getStackTrace(Throwable ex) {
		StringBuilder stackTrace = new StringBuilder();
		for (StackTraceElement element : ex.getStackTrace()) {
			stackTrace.append("\n\tat ").append(element);
		}
		return stackTrace.toString();
	}
}