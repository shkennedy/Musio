# Museo

Spotify on the web.

You will need maven, npm, and the angular-cli to build and run this project.


### First build the frontend
Run:

```
ng build
```

in the angular folder (where '.angular-cli.json' is).
It will create bundled output in the 'angular/dist' folder.


### Next, start up the backend.
Run:

```
mvn spring-boot:run
```

in the directory of pom.xml (the root directory). Maven will look in '/angular/dist' for bundled frontend output and it will copy these files to 'src/main/resources/static'. Look inside the pom.xml to see how we did this.





Good to know when developing: to view your frontend code changes without restarting the backend server, do:

```
ng build --output-path=../src/main/resources/static/
```

while in the angular folder. The backend will live-reload and serve up the updated resources.


The web server will be running on port 8080.

