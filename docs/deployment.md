# Deployment

# Production
Highly recommend to deploy in HTTPS due to security, clipboard copy, desktop notification

# Authentication and authorization
yanagishima doesn't have authentication/authorization feature.

But, if you have any reverse proxy server for yanagishima and that reverse proxy server provides HTTP level authentication, you can use it for yanagishima too.
yanagishima can log username for each query executions and authorize per datasource.

If your reverse proxy server sets username on HTTP header just after authentication, before proxied requests you can use it.

In this case, please specify ```audit.http.header.name``` which is http header name to be passed through your proxy.

If you want to deny to access without username, please specify ```user.require=true```

If you set ```check.datasource=true``` and datasource list which you want to allow on HTTP header ```X-yanagishima-datasources``` through your proxy, authorization feature is enabled.

For example, if there are three datasources(aaa and bbb and ccc) and ```X-yanagishima-datasources=aaa,bbb``` is set, user can't access to datasource ccc.

If you use a Trino with LDAP, you need to specify ```auth.xxx=true``` in your application.yml
```yaml
server.port: 8080
presto.datasources: your-presto
presto.coordinator.server.your-presto: http://presto.coordinator:8080
catalog.your-presto: hive
schema.your-presto: default
sql.query.engines: presto
auth.your-presto: true
```

# Multiple user sessions via reverse proxy

HTTP Basic authentication is handled by browsers and the same credential is
shared across all tabs.  If you need to connect to yanagishima with different
usernames at the same time, configure your proxy (for example Nginx) to pass a
per-session cookie or header that represents the authenticated user.

Below is a simplified Nginx snippet which forwards a cookie value as a custom
header to yanagishima:

```nginx
location / {
    auth_request /auth; # your authentication endpoint
    proxy_set_header X-Auth-User $cookie_session_user;
    proxy_pass http://localhost:8080;
}
```

Then enable header based auditing in `application.yml` so yanagishima picks up
the forwarded username:

```yaml
audit.http.header.name: X-Auth-User
use.audit.http.header.name: true
```

With this approach, each browser tab can use a different cookie and therefore a
different username.

