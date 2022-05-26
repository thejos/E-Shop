## PREREQUISITES

:black_small_square: MySQL Server&nbsp;version ***8.0 or later***<sup>&nbsp;_1_</sup> installed and running  
&emsp;&emsp;[Download](https://dev.mysql.com/downloads/mysql/) MySQL Server  
&emsp;&emsp;[Read/download](https://dev.mysql.com/doc/mysql-installation-excerpt/8.0/en/) MySQL Installation Guide.

:black_small_square: A database the application communicates with  
&emsp;&emsp;To import required database into MySQL server run the [`web\WEB-INF\ext_libraries\sql\eshoptest.sql`](web/WEB-INF/ext_libraries/sql/ "show the file location") SQL script.  
&emsp;&emsp;Learn how to import data/database in MySQL via [mysqlimport](https://dev.mysql.com/doc/refman/8.0/en/mysqlimport.html) client or via [MySQL Workbench](https://dev.mysql.com/doc/workbench/en/wb-admin-export-import-management.html).

:black_small_square: JDBC driver for MySQL  (MySQL Connector/J)<sup>&nbsp;_1_</sup>  
&emsp;&emsp;Download the connector from [here](http://dev.mysql.com/downloads/connector/j "MySQL homepage") or from [here](https://mvnrepository.com/artifact/mysql/mysql-connector-java "Maven repository").  
&emsp;&emsp;Learn [how to](https://www.geekinsta.com/how-to-connect-java-application-with-mysql-using-netbeans/#configuring-the-netbeans-project) add MySQL connector jar file to the current project  using NetBeans.  

:black_small_square: GlassFish Java application server - Full Platform edition  
&emsp;&emsp;Recommended versions: ***4.1.1***; ***5.0.1***;  
&emsp;&emsp;Download GlassFish server from [ here](https://javaee.github.io/glassfish/download)  
&emsp;&emsp;GlassFish Server [Quick Start Guide](https://javaee.github.io/glassfish/doc/4.0/quick-start-guide.pdf)  
<br>
:black_small_square: PrimeFaces for JavaServerFaces (JSF)   
&emsp;&emsp;PrimeFaces is a popular open source framework for JavaServerFaces featuring over 100 components.   
&emsp;&emsp;Download PrimeFaces ***5.0*** [here](https://mvnrepository.com/artifact/org.primefaces/primefaces/5.0) or [here](https://www.primefaces.org/downloads/)   
<br>   
&nbsp;<sup>_1_</sup> _<sub>During the application creation MySQL Community Server ***version 8.0.18*** and MySQL Connector/J ***version 8.0.19*** were used.<sub>_

