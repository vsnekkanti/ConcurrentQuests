import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.Deque;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class SimpleRateLimiter {
    private final int maxNumberOfRequests;         // Maximum number of requests allowed in a time period
    private final long timePeriodForRequests;      // How long the time period lasts in milliseconds
    private final Deque<Long> timesOfPastRequests; // A list to store the times of all recent requests

    // DateTimeFormatter to format time in human-readable form
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withLocale(Locale.US)
            .withZone(ZoneId.systemDefault());

    public SimpleRateLimiter(int maxNumberOfRequests, long timePeriodForRequests) {
        this.maxNumberOfRequests = maxNumberOfRequests;
        this.timePeriodForRequests = timePeriodForRequests;
        // Using a thread-safe list to keep track of the times when requests were made
        this.timesOfPastRequests = new ConcurrentLinkedDeque<>();
    }

    public boolean isRequestAllowed() {
        long currentTime = System.currentTimeMillis(); // Get the current time in milliseconds
        long elapsedTimeSeconds;

        // Remove any requests that happened before the current elapsed time
        long thresholdTime = currentTime - timePeriodForRequests; // Calculate the threshold time
        while (!timesOfPastRequests.isEmpty() && timesOfPastRequests.peekFirst() <= thresholdTime) {
            timesOfPastRequests.pollFirst();  // Remove old requests
        }

        // Calculate elapsed time in seconds from the oldest request
        if (!timesOfPastRequests.isEmpty()) {
            long oldestRequestTime = timesOfPastRequests.peekFirst();
            elapsedTimeSeconds = (currentTime - oldestRequestTime) / 1000; // Convert milliseconds to seconds
        } else {
            elapsedTimeSeconds = 0; // If no requests, elapsed time is 0
        }

        // Print the current contents of timesOfPastRequests and elapsed time in seconds for debugging
        printTimesOfPastRequests();
        System.out.println("Elapsed time: " + elapsedTimeSeconds + (elapsedTimeSeconds == 1 ? " second" : " seconds"));

        // Check if the number of requests in the current time period is below the maximum allowed
        if (timesOfPastRequests.size() < maxNumberOfRequests) {
            timesOfPastRequests.addLast(currentTime);  // Add the current request's time to the list
            System.out.println("Request allowed at: " + formatHumanReadableTime(currentTime));
            return true;
        } else {
            System.out.println("Request denied - too many requests at: " + formatHumanReadableTime(currentTime));
            return false;
        }
    }

    // Method to format the timestamp into a human-readable format
    private String formatHumanReadableTime(long timestamp) {
        return formatter.format(Instant.ofEpochMilli(timestamp));
    }

    // Method to print the contents of timesOfPastRequests in human-readable form for debugging
    private void printTimesOfPastRequests() {
        if (timesOfPastRequests.isEmpty()) {
            System.out.println("timesOfPastRequests is empty.");
        } else {
            System.out.println("Current request times:");
            for (Long requestTime : timesOfPastRequests) {
                System.out.println(formatHumanReadableTime(requestTime));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create a rate limiter that allows 5 requests every 10 seconds (10000 milliseconds)
        SimpleRateLimiter rateLimiter = new SimpleRateLimiter(5, 10000);

        // Simulate 10 requests
        for (int i = 1; i <= 20; i++) {
            if (rateLimiter.isRequestAllowed()) {
                System.out.println("Request " + i + " allowed.");
            } else {
                System.out.println("Request " + i + " denied.");
            }
            Thread.sleep(1000); // Wait 2 seconds between requests
        }
    }
}
