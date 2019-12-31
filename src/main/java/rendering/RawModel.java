package rendering;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RawModel {

    private int vaoID; // vertex array object
    private int vertexCount;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }
}
