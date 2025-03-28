/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.xxywithpq.infrastructure.cache.map;


import cn.xxywithpq.domian.cache.LocalCache;
import cn.xxywithpq.infrastructure.cache.LocalCacheManager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class MapCacheManager implements LocalCacheManager {

    @Resource
    private MapCache mapCache;

    @Override
    public LocalCache getLocalCache() {
        return mapCache;
    }
}
