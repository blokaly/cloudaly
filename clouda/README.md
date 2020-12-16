Description
---

Run a kotlin based springboot service as a docker instance on AWS Elastic Beanstalk Docker platform.
The kotlin applicaiton is based on bootiful-graphql-demo, see the below reference section for details.

Run
---

```shell
$ ./gradlew clean dockerCreateDockerfile copyDockerCompose
$ cd build/docker
$ eb init # select your aws region and the elastic beanstalk application
$ eb deploy
```

Reference
---

https://www.youtube.com/watch?v=1siPT1pTXFU
https://github.com/dariuszkuc/bootiful-graphql-demo

Note
---

- change debug to true will set the root logger level to debug
