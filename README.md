## 配置
**crud-core 在 application.yml 中的配置** 

```yaml
###################  am配置  ###################
am:
  ## 格式化时间的格式，如不配置，默认用yyyy-MM-dd HH:mm:ss
  #json-serialize-date-format: MM/dd/yyyy HH:mm:ss
  worker-id: 0 # id gen service machine id 0~1023
  redis-host: 127.0.0.1
  redis-port: 6379
  #redist-timeout: 300
  
---
am:
  shiro-cache-redis: true
  reset-admin-perms: false
```
