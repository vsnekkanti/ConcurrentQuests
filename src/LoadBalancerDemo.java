import java.util.ArrayList;
import java.util.List;

interface LoadBalancer {
    String getServer();
    void reportFailure(String server);
}

class RoundRobinLoadBalancer implements LoadBalancer {
    private List<String> servers;
    private int index = 0;

    public RoundRobinLoadBalancer(List<String> servers) {
        this.servers = servers;
    }

    // Get the next server in round-robin fashion
    public synchronized String getServer() {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available");
        }

        String server = servers.get(index);
        index = (index + 1) % servers.size();
        return server;
    }

    // Report failure and remove the server from the list
    public synchronized void reportFailure(String server) {
        System.out.println("Server failed: " + server);
        servers.remove(server);

        // Adjust index if necessary, ensuring it points to a valid server
        if (index >= servers.size()) {
            index = 0; // Reset to 0 if index is out of bounds after removal
        }
    }
}

public class LoadBalancerDemo {
    public static void main(String[] args) {
        List<String> servers = new ArrayList<>(List.of("Server1", "Server2", "Server3"));
        LoadBalancer lb = new RoundRobinLoadBalancer(servers);

        // Simulate getting servers
        System.out.println(lb.getServer()); // Server1
        System.out.println(lb.getServer()); // Server2

        // Simulate a server failure
        lb.reportFailure("Server2");

        // Continue round-robin
        System.out.println(lb.getServer()); // Server3
        System.out.println(lb.getServer()); // Server1 (wraps around)
        System.out.println(lb.getServer()); // Server3
    }
}
