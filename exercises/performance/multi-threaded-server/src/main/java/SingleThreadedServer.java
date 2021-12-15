import static spark.Spark.*;
public class SingleThreadedServer {

    public static void main(String[] args) {
        get("/loop", (req, res) -> loop());        // TODO
    }

    private static String loop(){
        for (int i =0; i< Integer.MAX_VALUE; i++){
            continue;
        }
        return "Finished";
    }
}
