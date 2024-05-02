package restService.response;

public class DeleteGameResponse {
    private final boolean success;
    private final String errorMessage;

    public DeleteGameResponse(boolean success, String errorMessage) {
        this.success = success;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
