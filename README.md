
# oauth2 server

## How to build and run:
> mvn clean install docker:build <br>
> docker-compose -f docker-compose.yml up -d


## Grant Types : 

##### client_credentials: 

> **use         :** machine to machine <br>
> **description :** The simplest of all of the OAuth 2.0 grants, this grant is suitable for machine-to-machine authentication where a specific user’s permission to access data is not required.
   
##### implicit:

> **use         :** user-agent-based clients (e.g. single page web apps) <br>
> **description :** It is intended to be used for user-agent-based clients (e.g. single page web apps) that can’t keep a client secret because all of the application code and storage is easily accessible.<br>
              Secondly instead of the authorization server returning an authorization code which is exchanged for an access token, the authorization server returns an access token.

##### authorization_code:
> **use         :** <br>
> **description :** The authorization code grant should be very familiar if you’ve ever signed into an application using your Facebook or Google account.


##### refresh_token:
> **use         :** refresh expired token <br>
> **description :** Access tokens eventually expire; however some grants respond with a refresh token which enables the client to get a new access token without requiring the user to be redirected.

##### password:
> **use        :** native device applications,secure web application<br>
> **defination :** This grant is a great user experience for trusted first party clients both on the web and in native device applications.



#### Client credentials grant type : 
```
    curl -u curl_client:user -X POST localhost:9000/oauth/token -d grant_type=client_credentials
```

#### Implicit grant grant type : 
```
   http://localhost:9000/oauth/authorize?response_type=token&client=curl_client&redirect_uri=<redirect_url>
```

#### authorization_code:
```
http://localhost:9000/oauth/authorize?response_type=code&client_id=curl_client&redirect_uri=http://127.0.0.1

curl-v -u curl_client:user -X POST localhost:9000/oauth/token  \
-d grant_type=authorization_code -d client_id=curl_client     \
-d redirect_uri=http://127.0.0.1 -d code=<code>
```

#### Refresh token grant type : 
```
 curl -u curl_client:user -X POST localhost:9000/oauth/token -d grant_type=refresh_token -d refresh_token=4b0851a5-d535-4783-9a31-65b90ab09714
```

#### Password grant type : 
```
    curl -v -u curl_client:user -X POST localhost:9000/oauth/token -d "grant_type=password&username=user_admin&password=user"
```

#### Links:

 https://github.com/spring-guides/tut-spring-security-and-angular-js/tree/master/oauth2-vanilla<br>
 https://alexbilbie.com/guide-to-oauth-2-grants/<br>
 http://www.swisspush.org/security/2016/10/17/oauth2-in-depth-introduction-for-enterprises<br>
 https://javadeveloperzone.com/?s=oauth2<br>
 https://spring.io/guides/tutorials/spring-boot-oauth2/<br>
 https://memorynotfound.com/spring-security-user-registration-example-thymeleaf/<br>

