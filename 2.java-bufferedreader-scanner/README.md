# Java BufferedReader와 Scanner의 성능 차이 비교

기간 2024.11.18 ~ 2024.11.19

Java에서 입력을 받는 방법으로는 BufferedReader와 Scanner가 있습니다. 이 둘 중에서 Java로 알고리즘을 풀어보는 사람들은 BufferdReader가 더 좋은 입력 성능을 보여주기 때문에, 이를 사용하라는 말을 많이 듣습니다. 이번 조사는 **어떤 이유 때문에 BufferedReader는 Scanner보다 왜 빠를까?** 에 대해서 다루고 있습니다.

과연 자주 알려져있는 **버퍼의 사이즈 때문에 성능 차이가 발생한다**가 그 핵심적인 원인일까요?

다른 프로젝트들과 달리 구현된 클래스 및 공식 문서 참고가 많아 여러가지 예제가 존재하지는 않기 때문에, `src/main/java/com/kk` 아래에 모든 클래스를 배치했습니다.

각각의 클래스들의 의미는 아래 표와 같습니다.

| 클래스명 | 내용 |
|---------|-----|
| ScannerExample | Scanner를 이용해 30MB에 해당하는 텍스트 파일을 읽고 출력하는 시간을 기록합니다. |
| BufferedReaderExample | BufferedReader를 이용해 30MB에 해당하는 텍스트 파일을 읽고 이를 출력하는 시간을 기록합니다. 버퍼의 사이즈를 Scanner와 동일한 1024로 설정한 후 테스트한 결과도 포함합니다. |
| ScannerIntegerParseExample | Scanner가 `1,234,567`과 같은 서식이 포함된 숫자도 파싱할 수 있다는 것을 보여줍니다. 수를 변환시키는 `nextInt()` 의 구현부를 살펴보시면 정규표현식으로 `,`를 사용하는 것을 확인할 수 있습니다. |
| StringTokenExample | Scanner가 느린 주된 원인이 **정규표현식 사용**라고 생각했습니다. 유사한 사례인 `StringTokenizer`와 `String.split()`의 성능 차이를 비교해봤습니다. | 

이 프로젝트에서 다루지 않은 많은 내용은 [이 링크](https://dev-qhyun.tistory.com/33)를 통해서 확인하실 수 있습니다.
