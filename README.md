# oak-hybrid-cache

## 简介(Introduction)

**oak-hybrid-cache** 是`基于集群环境下的混合缓存框架`, 通过本地缓存与分布式缓存相结合, 将数据前置到每一个集群服务节点,
降低数据库访问压力, 同时尽量保证数据一致性(弱一致性)。

**oak-hybrid-cache** is `the hybrid cache framework based on cluster environment`. It preloads data to each cluster
service node through a combination of local cache and distributed cache.
reduce database access pressure while ensuring data consistency (weak consistency).

## 特性(Features)

- 项目基于 [alibaba/COLA V4](https://github.com/alibaba/COLA) 应用架构。(project based
  on [alibaba/COLA V4] (https://github.com/alibaba/COLA) application architecture.)
- 在集群环境下, 为了应对流量洪峰, 以本地缓存为主, 分布式缓存为辅, 本地缓存与分布式缓存相结合。(In a cluster environment,
  to cope with traffic peaks, local cache is the primary component, and distributed cache is the secondary component.
  Local cache and distributed cache are combined.)
  - 本地缓存: 基于内存的缓存(Caffeine), 作为第一道缓存防线, 用于快速访问, 没有远程IO, 性能卓越。(Local cache: Memory
    based cache (Caffeine), as the first line of cache defense, for fast access, no remote IO, excellent performance.)
  - 分布式缓存: 基于分布式缓存(Redis), 作为第二道缓存防线, 同时也负责协调和同步最新数据到本地缓存。(Distributed Cache:
    Based on Distributed cache (Redis), it acts as a second line of cache defense and is also responsible for
    coordinating and synchronizing the latest data to the local cache.)

## 使用要求(Requirements)

## 用法(How to use)

