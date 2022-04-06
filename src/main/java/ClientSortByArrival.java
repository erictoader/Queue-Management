import java.util.Comparator;

public class ClientSortByArrival implements Comparator<Client> {
    @Override
    public int compare(Client o1, Client o2) {
        return o1.getTArrival() - o2.getTArrival();
    }
}
