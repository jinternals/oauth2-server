FROM java:8-jre
MAINTAINER Mradul Pandey <pandeymradul@gmail.com>
LABEL git_commit_id="${git.commit.id.abbrev}"

LABEL app.name="@project.name@"
LABEL app.groupId="@project.groupId@"
LABEL app.artifactId="@project.artifactId@"
LABEL app.version="@project.version@"

ADD ./cloud-example-authserver.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/cloud-example-authserver.jar"]

EXPOSE 9000