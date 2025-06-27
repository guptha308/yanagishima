# Configuration
Spring Boot is used as a main framework and MySQL instance is required. See [official documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-access) to configure the datasource.

This guide focuses on a single Trino setup and removes options for other engines.

You need to edit `application.yml` file.
```yaml
# yanagishima web port
server.port: 8080
# 30 minutes. If presto query exceeds this time, yanagishima cancel the query.
presto.query.max-run-time-seconds: 1800
# 1GB. If presto query result file size exceeds this value, yanagishima cancel the query.
presto.max-result-file-byte-size: 1073741824
# you can specify freely. But you need to specify same name to presto.coordinator.server.[...] and presto.redirect.server.[...] and catalog.[...] and schema.[...]
presto.datasources: your-presto
# presto coordinator url
presto.coordinator.server.your-presto: http://presto.coordinator:8080
# almost same as presto coordinator url. If you use reverse proxy, specify it
presto.redirect.server.your-presto: http://presto.coordinator:8080/ui
# presto catalog name
catalog.your-presto: hive
# presto schema name
schema.your-presto: default

# presto user
user.your-presto: yanagishima
# presto source
source.your-presto: yanagishima

# if query result exceeds this limit, to show rest of result is skipped
select.limit: 500
# http header name for audit log
audit.http.header.name: some.auth.header
# limit to convert from tsv to values query
to.values.query.limit: 500
# authorization feature
check.datasource: false
# CORS setting
cors.enabled: false
sql.query.engines: presto
```

## Single Trino cluster
```yaml
server.port: 8080
presto.datasources: your-presto
presto.coordinator.server.your-presto: http://presto.coordinator:8080
presto.redirect.server.your-presto: http://presto.coordinator:8080/ui
catalog.your-presto: hive
schema.your-presto: default
sql.query.engines: presto
```
