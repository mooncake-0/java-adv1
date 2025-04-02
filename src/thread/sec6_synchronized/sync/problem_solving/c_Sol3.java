package thread.sec6_synchronized.sync.problem_solving;

// 역시 생각해보는 문제다
// 다음 ImmutableVar 의 멤버 변수는 멀티 스레드 상황에서 문제가 될 수 있을까?
// 내 답변
// 1 - 불변이기 때문에, 문제가 되지 않는다.
// 2(XX) - 다만, 메모리 가시성 문제는 존재한다. (불변 값이라 바뀌지 않을텐데 가시성 문제가 왜있겠니 바보얌...)

// 해설
// "항상 읽기"를 위해 접근하는건 별 문제를 야기하지 않는다
// 항상 수정을 하기 때문에 문제가 되는거임

public class c_Sol3 {

    // final 로 변하지 않는 속성을 가지고 있다
    static class ImmutableVar{
        private final int value;

        public ImmutableVar(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
