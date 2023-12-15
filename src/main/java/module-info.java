module com.example.ai_mcproblem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ai_mcproblem to javafx.fxml;
    exports com.example.ai_mcproblem;
}