/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.agent.stream.worker.register;

import org.skywalking.apm.collector.cache.CacheServiceManager;
import org.skywalking.apm.collector.storage.table.register.ServiceName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author peng-yongsheng
 */
public class ServiceNameService {

    private final Logger logger = LoggerFactory.getLogger(ServiceNameService.class);

    private final CacheServiceManager cacheServiceManager;

    public ServiceNameService(CacheServiceManager cacheServiceManager) {
        this.cacheServiceManager = cacheServiceManager;
    }

    public int getOrCreate(int applicationId, String serviceName) {
        int serviceId = cacheServiceManager.getServiceIdCacheService().get(applicationId, serviceName);

        if (serviceId == 0) {
            ServiceName service = new ServiceName("0");
            service.setApplicationId(applicationId);
            service.setServiceName(serviceName);
            service.setServiceId(0);
        }
        return serviceId;
    }
}
