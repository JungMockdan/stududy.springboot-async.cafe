# async와 비지니스 로직을 적용을 위한 패턴 적용
- spring async
- CompletableFuture
- 전략패턴
- RuleEngine 패턴

## postman test
- url : [post] localhost:8080/order
- request body : raw>json
    - {"id":123}
  
## 음...?
- UseCaseAStrategy 클래스 내부에 EventServerCallerFeign을 주입하고 싶다.

- BUT, 모든 전략을 List에 담아두고 전략을 판별하는 전략엔진StrategyEngine.clas 구조상 모든 전략은 이미 생성되어 리스트에 들어가 있어야 한다. 각전략에 업무프로세스를 포함하는 메서드를 포함하고 있다. Enum으로 처리하기엔 무리/별로이기에 클래스로 별도로 분리한것임.
- 결국, 디자인모델이 현재 업무와 맞지 않는 성격인건가???? 개선의 여지가 있는가? 있다면 어떻게?==> feign을 전달하지 않고, 객체에서 생성하도록.... , 없다면 어떻게? ==> 새로운 모델을 구상해볼 것...아니면 그냥 조건 남발...로.....



