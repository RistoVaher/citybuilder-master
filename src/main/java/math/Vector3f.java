package math;

import lombok.Data;

@Data
public class Vector3f {

    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f() { // default values
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

}
