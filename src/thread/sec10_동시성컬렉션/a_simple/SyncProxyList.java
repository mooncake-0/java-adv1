package thread.sec10_동시성컬렉션.a_simple;

// SimpleList 를 받아서 하는 일에 Sync 처리만 지원해주는 클래스
public class SyncProxyList implements SimpleList {

    private SimpleList target;

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }

    @Override
    public synchronized int size() {
        return this.target.size();
    }

    @Override
    public synchronized void add(Object obj) {
        this.target.add(obj);
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public String toString() {
        return target.toString() + " + by " + this.getClass().getSimpleName();
    }
}
