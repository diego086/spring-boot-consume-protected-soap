#Consuming a Soap Service using spring boot with soap security
There is a lot of information regarding consuming a SOAP service with java. But it was hard for me at least find good documentation or a "how to do" tutorial to consume a SOAP service with soap authentication.

So using spring boot i did a pov project that consumes a real SOAP service which is SOAP protected. At least in a easy way where we dont have to construct manually the SOAP headers.

Cutting to the chase, what we need to use is a Wss4jSecurityInterceptor that "injects" the credential to the request when we want to consume the service.