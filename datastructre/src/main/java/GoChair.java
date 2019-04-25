import java.util.Objects;

public class GoChair {

    private String seat1Name;
    private String seat2Name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoChair)) return false;
        GoChair goChair = (GoChair) o;
        return Objects.equals(seat1Name, goChair.seat1Name) &&
                Objects.equals(seat2Name, goChair.seat2Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seat1Name, seat2Name);
    }

    public GoChair(String seat1Name, String seat2Name) {
        this.seat1Name = seat1Name;
        this.seat2Name = seat2Name;
    }


}
