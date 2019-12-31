package math;

import lombok.Data;

@Data
public class Vector2f {

    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f() { // default values
        this.x = 0.0f;
        this.y = 0.0f;
    }

}
