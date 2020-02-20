# Servlet Scopes

Servlet scopes define a way for data to be persisted and propagated throughout
a servlet lifecycle.  There are four servlet scopes.

1. Request Scope
2. Session Scope
3. Servlet Scope
4. Application Scope

## Request Scope

Request scope data is used when we need to store data from one part of processing
a request for access in another part. This is useful in situations where you perhaps you want
to authenticate a user token in the service method, but you need to use the user credentials
in the doGet/doPost/etc method. It could also be useful if you need to transfer or pass data during
the process of forwarding a request. Data in the request scope is stored on the request object.
Request scope data is only valid for a single request's lifetime.

## Session Scope

Session scope stores data for the lifetime of a single user session. Session scope variables
are stored in the HttpSession. Sessions are identified by a JSESSIONID cookie. Note: If your
server restarts (which it does a lot during development) this data is gone!

## Servlet Scope

Servlet scope data is data stored and scoped to a specific servlet.  This data is often
used to configure a servlet during its initialization.  Servlet scope data can be provided
to the servlet in the servlet's web.xml declaration. They can also be accessed programmatically.

## Application Scope (aka context scope)

Application scoped data is data scoped to the entire application. It is occasionally used
for application wide configuration.  This data is generally declared in the web.xml config
and can be accessed programmatically.
























