package model;

import java.util.List;

public class Picture {

    private final static List<String> pictures = List.of(
            """
            ____________
            |/       |
            |       
            |       
            |      
            |       
            |      
            |      
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |       
            |     
            |       
            |      
            |      
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |        |
            |        | 
            |        |  
            |      
            |     
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |       _|
            |      / | 
            |     /  |
            |       
            |      
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |       _|_
            |      / | \\
            |     /  |  \\
            |       
            |      
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |       _|_
            |      / | \\
            |     /  |  \\
            |       / 
            |     _/  
            |
            """,

            """
            ____________
            |/       |
            |       (_)
            |       _|_
            |      / | \\
            |     /  |  \\
            |       / \\
            |     _/   \\_
            |
            """

    );

    private Picture() {}



    public static List<String> getPictures() {
        return pictures;
    }
}
