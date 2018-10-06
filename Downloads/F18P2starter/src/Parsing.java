
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * arrayRecord file to be inserteded in slots in the hashTable
 * 
 * @author Hisham Juneidi <hishamj6>
 * @version (2018-09-4)
 **/
public class Parsing {

    /**
     * constructor
     */
    public Parsing() {
        // defult
    }

    /**
     * parses command
     * 
     * @param command
     *            command being parsed
     * @param movies
     *            movie hashtable
     * @param critics
     *            reviewer hashtable
     * @param sm
     *            sparse matrix
     */
    public void 
        parse(String command, Hash movies, Hash critics, SparseMatrix sm) {
        String[] array;
        File file = new File(command);

        try {

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String i = sc.nextLine().trim();
                if (!i.equals("")) {
                    String last = i.replaceAll("\\s+<SEP>", "<SEP>");
                    String lastF = last.replaceAll("<SEP>\\s+", "<SEP>");
                    array = lastF.trim().split("\\s+");
                    if (array[0].equals("print")) {
                        if (array[1].equals("hashtable")) {
                            if (array[2].equals("reviewer")) {
                                System.out.println("Reviewers:");
                                critics.hashPrint();
                            } 
                            else { // if movie
                                System.out.println("Movies:");
                                movies.hashPrint();
                            }
                        } 
                        else if (array[1].equals("ratings")) {
                            sm.printRating();
                        }
                    } 
                    else if (array[0].equals("delete")) {
                        StringBuilder longStr = new StringBuilder();
                        for (int l = 1; l < array.length; l++) {
                            longStr.append(array[l] + " ");
                        }
                        int rIndex = longStr.indexOf("reviewer");
                        // delete from movie hash
                        if (rIndex == -1) {
                            int mIndex = longStr.indexOf("movie");
                            StringBuilder movieBuild = new StringBuilder();
                            for (int j = mIndex + 5; j < longStr.length();
                                    j++) {
                                movieBuild.append(longStr.charAt(j));
                            }
                            String movie = movieBuild.toString().trim();
                            boolean deleted = movies.hashDelete(movie, sm);
                            if (deleted) {
                                System.out.print("|" + movie + 
                                        "| has been deleted ");
                                System.out.println("from the Movie database.");
                                //sm.deleteMovie(movie);
                            } 
                            else {
                                System.out.print("|" + movie + 
                                        "| not deleted because ");
                                System.out.println("it does not exist "
                                        + "in the Movie database.");
                            }

                        }
                        // delete from reviewer hash
                        else {
                            StringBuilder reviewerBuild = new StringBuilder();
                            for (int j = rIndex + 8; j < longStr.length(); 
                                    j++) {
                                reviewerBuild.append(longStr.charAt(j));
                            }
                            String reviewer = reviewerBuild.toString().trim();
                            boolean deleted = critics.hashDelete(reviewer, sm);
                            if (deleted) {
                                System.out.print("|" + reviewer + 
                                        "| has been deleted ");
                                System.out.println("from the "
                                        + "Reviewer database.");
                                //sm.deleteReviewer(reviewer);
                            } 
                            else {
                                System.out.print("|" + reviewer + 
                                        "| not deleted because ");
                                System.out.println("it does not "
                                        + "exist in the Reviewer database.");
                            }

                        }
                    } 
                    else if (array[0].equals("add")) {
                        StringBuilder longStr = new StringBuilder();
                        StringBuilder mv = new StringBuilder();
                        StringBuilder reviewer = new StringBuilder();
                        StringBuilder ratingS = new StringBuilder();
                        for (int l = 1; l < array.length; l++) {
                            longStr.append(array[l] + " ");
                        }
                        int sep = longStr.indexOf("<SEP>");
                        for (int j = 0; j < sep; j++) {
                            reviewer.append(longStr.charAt(j));
                        }
                        int sep2 = longStr.indexOf("<SEP>", sep + 5);
                        for (int j = sep + 5; j < sep2; j++) {
                            mv.append(longStr.charAt(j));
                        }
                        for (int j = sep2 + 5; j < longStr.length(); j++) {
                            ratingS.append(longStr.charAt(j));
                        }
                        int rating = Integer.parseInt(
                                ratingS.toString().trim());

                        if (rating >= 1 && rating <= 10) {
                            ArrayRecord critic = new ArrayRecord(
                                    reviewer.toString(), false);
                            ArrayRecord movie = new ArrayRecord(mv.toString(), true);
                            critics.hashInsert(critic, sm);
                            movies.hashInsert(movie, sm);
                            movies.addRating(mv.toString(), 
                                    reviewer.toString(), rating, sm, critics);
                            System.out.print("Rating added: |" + 
                                    reviewer + "|, |");
                            System.out.println(mv + "|, " + rating);
                        } 
                        else {
                            System.out.println("Bad score |" + 
                                    rating + "|. Scores "
                                            + "must be between 1 and 10.");
                        }
                        // entry.setSize(longStr.length());

                    } 
                    else if (array[0].equals("update")) {
                        StringBuilder addStr = new StringBuilder();
                        for (int l = 2; l < array.length; l++) {
                            addStr.append(array[l] + " ");
                        }
                        addStr.deleteCharAt(addStr.length() - 1);
                    } 
                    else if (array[0].equals("list")) {
                        if (array[1].equals("movie")) {
                            StringBuilder movie = new StringBuilder();
                            for (int x = 2; x < array.length; x++) {
                                movie.append(array[x] + " ");
                            }
                            sm.listMovie(movie.toString().trim());
                        }
                        else {
                            StringBuilder critic = new StringBuilder();
                            for (int x = 2; x < array.length; x++) {
                                critic.append(array[x] + " ");
                            }
                            sm.listReviewer(critic.toString().trim());
                        }
                    }

                }
            }

            sc.close();

        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
