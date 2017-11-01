# Museo

Spotify on the web.

You will need maven, npm, and the angular-cli to build and run this project.


### First build the frontend
In the 'angular/' folder (where '.angular-cli.json' and 'package.json' are), run:

```
npm install
ng build
```

It will create bundled frontend output in the 'angular/dist' folder.


### Next, start up the backend.
In the directory of pom.xml, run:

```
mvn spring-boot:run
```

Maven will look in '/angular/dist' for bundled frontend output and it will copy these files to 'src/main/resources/static'. Look inside the pom.xml to see how we did this.

The web server will be running on port 8080!




**Good to know when developing**: To have your frontend changes automatically reflected in the browser, run:

```
npm run build:watch:dev
```

while in the 'angular/' folder. Passing the '--watch' and '--output-path' flags make the angular-cli watch for file changes and automatically copy the new bundled output into the backend server's resources. You can see what the actual command is in package.json.

You can also get the LiveReload chrome extension to make your browser automatically refresh when this happens.
