# Ecobee Ratings

## Run

### On Linux

Add execution permission to `gradlew`

```shell
sudo chmod +x gradlew
```

Run with the provided example `input.txt`:
```shell
./gradlew run --args=input.txt
```
or
```shell
./gradlew run --args=</absolute/path/to/another/input.txt>
```

### Output
![Output from example](resources/images/run-main.png?raw=true)

## Test

Runs all tests

```shell
gradlew test
```

### Reports

![Tests report by package](resources/images/tests-report-packages.png?raw=true)
![Tests report by classes](resources/images/tests-report-classes.png?raw=true)
