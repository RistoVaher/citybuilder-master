package math;

import static java.lang.Math.*;

// 4 by 4 matrix
public class Matrix4f {

    public static final int LENGTH = 4 * 4; // might be used elsewhere hence public
    public float[] elements = new float[LENGTH];

    public Matrix4f() {

    }

    public static Matrix4f identity() { // identify matrix, basically all elements in the main diagonal are equal to 1, everything else 0.
        Matrix4f result = new Matrix4f();
        for (int i = 0; i < LENGTH; i++) {
            result.elements[i] = 0.0f; // not that necessary, but gonna keep it here.
        }
        // diagonally creating the matrix
        // first value refers to the row, second to the column, multiplied with the constant width of the matrix.
        result.elements[0 + 0 * 4] = 1.0f; // this could of been 0, but this way it makes much more sense.
        result.elements[1 + 1 * 4] = 1.0f;
        result.elements[2 + 2 * 4] = 1.0f;
        result.elements[3 + 3 * 4] = 1.0f;

        return result;

    }

    public Matrix4f multiply(Matrix4f matrix) {
        Matrix4f result = new Matrix4f();
        // first 2 loops go thru each of the elements in the row and column
        // the 3rd multiplies the elements together and adds them to the overall sum of the resulting element / matrix
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; y++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += this.elements[e + y * 4] * matrix.elements[x + e * 4];
                }
            }
        }
        return result;
    }

    // NOTE: OpenGL typically takes column major ordering for matricies, basically means that the elements are ordered moving down the column not across rows
    public static Matrix4f translate(Vector3f vector) {
        Matrix4f result = identity();

        result.elements[0 + 3 * 4] = vector.getX();
        result.elements[1 + 3 * 4] = vector.getY();
        result.elements[2 + 3 * 4] = vector.getZ();

        return result;
    }

    //TODO: create a 3 methods for each axis seperately

    //Didn't comment this method too much cause i don't get all the math yet, gonna read more about it tomorrow.
    public static Matrix4f rotate(float angle, float x, float y, float z) { // rotates around all the axis
        Matrix4f result = identity();
        float r = (float) toRadians(angle);
        float cos = (float) cos(r);
        float sin = (float) sin(r);
        float omc = 1.0f - cos; // one minus cos

        result.elements[0 + 0 * 4] = x * omc + cos;
        result.elements[1 + 0 * 4] = y * x * omc + z * sin;
        result.elements[2 + 0 * 4] = x * z * omc - y * sin;

        result.elements[0 + 1 * 4] = x * y * omc - z * sin;
        result.elements[1 + 1 * 4] = y * omc + cos;
        result.elements[2 + 1 * 4] = y * z * omc + x * sin;

        result.elements[0 + 2 * 4] = x * z * omc + y * sin;
        result.elements[1 + 2 * 4] = y * z * omc - x * sin;
        result.elements[2 + 2 * 4] = z * omc + cos;

        return result;
    }

    public static Matrix4f rotateZ(float angle) { // rotates the Z axis
        Matrix4f result = identity();
        float r = (float) toRadians(angle);
        float cos = (float) cos(r);
        float sin = (float) sin(r);

        result.elements[0 + 0 * 4] = cos;
        result.elements[1 + 0 * 4] = sin;

        result.elements[0 + 1 * 4] = -sin;
        result.elements[1 + 1 * 4] = cos;

        return result;
    }


    public static Matrix4f project(float left, float right, float bottom, float top, float near, float far) { // orthographic projection matrix {left, right, bottom, top, near, far plane]
        Matrix4f result = identity();

        result.elements[0 + 0 * 4] = 2.0f / (right - left);

        result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

        result.elements[2 + 2 * 4] = 2.0f / (near - far);

        result.elements[0 + 3 * 4] = (left + right) / (left - right);

        result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);

        result.elements[2 + 3 * 4] = (far + near) / (far - near);

        return result;
    }

}
