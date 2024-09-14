package DataStructures;
import java.util.List;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;


public class Tuple implements Iterable<Object>, Comparable<Tuple> {
    private Comparator<Tuple> comparator = null;
    private final List<Object> elements;

    public Tuple(Object... elements) {
        this.elements = Arrays.asList(elements);
    }

    public Object get(int index) {
        if (index < 0 || index >= elements.size())
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        return elements.get(index);
    }

    public void setComparator(Comparator<Tuple> comparator) { this.comparator = comparator; }
    public int size() { return elements.size(); }
    public Iterator<Object> iterator() { return elements.iterator(); }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element : elements) {
            if (element instanceof String) sb.append("\"").append(element).append("\"");
            else if (element instanceof Character) sb.append("'").append(element).append("'");
            else sb.append(element);
            sb.append(", ");
        }
        return "(" + sb.substring(0, sb.length() - 2) + ")";
    }

    @SuppressWarnings("all")
    public int compareTo(Tuple o) {
        if (this.comparator != null) return this.comparator.compare(this, o);
        if (this.size() != o.size()) return this.size() - o.size();
        for (int i = 0; i < this.size(); i++)
            if (!this.get(i).equals(o.get(i)))
                return ((Comparable) this.get(i)).compareTo(o.get(i));
        return 0;
    }
}