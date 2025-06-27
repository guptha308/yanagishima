<p align="center">
    <img alt="Yanagishima Logo" src="docs/images/yanagishima.png" width="25%" />
</p>
<p align="center">Yanagishima is an open-source Web application for Trino.</p>
<p align="center">Visit <a href="https://yanagishima.github.io/yanagishima">the official web site</a> for more information.</p>
<p align="center">
   <a href="https://github.com/yanagishima/yanagishima/actions?query=workflow%3ACI+event%3Apush+branch%3Amaster">
       <img src="https://github.com/yanagishima/yanagishima/workflows/CI/badge.svg" alt="CI" />
   </a>
   <a href="http://www.youtube.com/watch?v=SoneFYNCXJEr">
       <img src="https://img.shields.io/badge/YouTube-Video-FF0000" alt="YouTube Video" />
   </a>
</p>

# Build requirements

* Java 17
* Node.js (18+ required to build the front-end)

## Quick Start
```
git clone https://github.com/yanagishima/yanagishima.git
cd yanagishima
git checkout -b [version] refs/tags/[version]
./gradlew distZip
cd build/distributions
unzip yanagishima-[version].zip
cd yanagishima-[version]
vim config/application.yml
nohup bin/yanagishima-start.sh >y.log 2>&1 &
```
see http://localhost:8080/

# Docker Quick Start
If you have Docker installed, you can spin up Trino and Yanagishima with a
single command.

```
docker-compose up
```

Then open <http://localhost:8080> in your browser.

# Stop
```
bin/yanagishima-shutdown.sh
```
