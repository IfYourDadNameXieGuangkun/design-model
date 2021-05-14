package exception.aye.model.dto;

public class ControllerResultDto {

    public static CR<?> create(){
        return new CR<>();
    }
    public static CR<?> create(Object data){
        return new CR<>(data);
    }

}
