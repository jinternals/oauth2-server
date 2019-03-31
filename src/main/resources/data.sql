INSERT INTO authority  VALUES(1,'ROLE_OAUTH_ADMIN');
INSERT INTO authority VALUES(2,'ROLE_RESOURCE_ADMIN');
INSERT INTO authority VALUES(3,'ROLE_PRODUCT_ADMIN');
INSERT INTO authority VALUES(4,'ROLE_ADMIN_DASHBOARD');

INSERT INTO user VALUES(1,b'1','pandeymradul@gmail.com','Mradul','Pandey','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO user VALUES(2,b'1','resource_admin@jinternals.com','first','last','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO user  VALUES(3,b'1','user_admin@jinternals.com','first','last','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');
INSERT INTO user  VALUES(4,b'1','dashboard_admin@jinternals.com','first','last','$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2','0');

INSERT INTO user_authorities VALUE (1,1);
INSERT INTO user_authorities VALUE (2,2);
INSERT INTO user_authorities VALUE (3,3);
INSERT INTO user_authorities VALUE (4,4);


INSERT INTO oauth_client_details VALUES('curl_client','user_api', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'read,write', 'password,authorization_code,refresh_token,implicit', 'http://127.0.0.1', 'ROLE_PRODUCT_ADMIN', 7200, 0, NULL, 'true');
INSERT INTO oauth_client_details VALUES('admin_dashboard_client','admin_dashboard_resource', '$2a$10$BurTWIy5NTF9GJJH4magz.9Bd4bBurWYG8tmXxeQh1vs7r/wnCFG2', 'openid', 'authorization_code,refresh_token,password', '', 'ROLE_ADMIN_DASHBOARD', 7200, 0, NULL, 'true');
