package ratings;

import java.util.HashMap;
import java.util.ArrayList;
import ratings.datastructures.Queue;

public class DegreesOfSeparation {
    private ArrayList<Movie> movieList;

    public DegreesOfSeparation(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public int degreesOfSeparation(String castName1, String castName2) {
        //the data should return all the casts members relationship to each other
        HashMap<String, ArrayList<String>> data = buildData();

        if (!((data.containsKey(castName1)) && (data.containsKey(castName2)))) {
            return -1;

        } else if (castName1.equals(castName2)) {
            return 0;

        } else {
            int distance = 0;
            Queue<String> queue = new Queue<>();
            ArrayList<String> explored = new ArrayList<>();
            queue.enqueue(castName2);

            while (!queue.toString().isEmpty()) {
                /*
                The TA suggests me to use Queue method
                */
                distance++;
                Queue<String> newQueue = new Queue<>();
                /*
                Use while loop to loop through because it
                doesn't have the size() method to run the
                for loop.

                run until there are no more to dequeue
                */
                while (!queue.toString().isEmpty()) {
                    ArrayList<String> related_node = data.get(queue.dequeue());
                    for (int c = 0; c < related_node.size(); c++) {
                        String cur_cast = related_node.get(c);
                        if (cur_cast.equals(castName1)) {
                            return distance;
                        } else if (!explored.contains(cur_cast)) {
                            /*
                            add the found related node to the nextQueue, so
                            that in the next iteration, it'll use the queue
                            I have now.
                            */
                            explored.add(cur_cast);
                            newQueue.enqueue(cur_cast);
                        }
                    }
                }
                queue = newQueue;
            }
            return -1;
        }
    }

    private HashMap<String, ArrayList<String>> buildData() {

        HashMap<String, ArrayList<String>> data = new HashMap<>();
        /*
        1. Render each node in the arraylist
        2. Get the arraylist of casts and add related casts members into maps
        */
        for (int i = 0; i < movieList.size(); i++) {
            ArrayList<String> castList = movieList.get(i).getCast();
            for (int l = 0; l < castList.size(); l++) {
                /*
                if each cast member has not been added yet, add them
                to the data as well as the following value of an
                arraylist of the related casts(excluding himself).
                */
                String cur_cast = castList.get(l);
                if (!data.containsKey(cur_cast)) {
                    data.put(cur_cast, new ArrayList<>());
                }
                //excluding himself
                for (int k = 0; k < castList.size(); k++) {
                    String new_cast = castList.get(k);
                    //added excluding the possibility of duplicated cast member
                    if ((!new_cast.equals(cur_cast)) || (!data.get(cur_cast).contains(new_cast))) {
                        data.get(cur_cast).add(new_cast);
                    }
                }
            }
        }
        return data;
    }
}