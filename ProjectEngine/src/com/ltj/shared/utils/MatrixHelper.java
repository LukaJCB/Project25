package com.ltj.shared.utils;



public class MatrixHelper {
	public static void perspectiveM(float[] m, float yFovInDegrees, float aspect,
			float n, float f) {
		final float angleInRadians =  (float) (yFovInDegrees * Math.PI / 180.0);

		final float a = (float) (1.0 / Math.tan(angleInRadians / 2.0));
		m[0] = a / aspect;
		m[1] = 0f;
		m[2] = 0f;
		m[3] = 0f;

		m[4] = 0f;
		m[5] = a;
		m[6] = 0f;
		m[7] = 0f;

		m[8] = 0f;
		m[9] = 0f;
		m[10] = -((f + n) / (f - n));
		m[11] = -1f;

		m[12] = 0f;
		m[13] = 0f;
		m[14] = -((2f * f * n) / (f - n));
		m[15] = 0f;        
	}

	public static void setIdentityM(float[] m){
		m[0] = 1;
		m[1] = 0;
		m[2] = 0;
		m[3] = 0;

		m[4] = 0;
		m[5] = 1;
		m[6] = 0;
		m[7] = 0;

		m[8] = 0;
		m[9] = 0;
		m[10] = 1;
		m[11] = 0;

		m[12] = 0;
		m[13] = 0;
		m[14] = 0;
		m[15] = 1;
	}




	public static void multiplyMM(float[] result, float[] a, float[] b) {
		final float b00 = b[0+0*4];
		final float b10 = b[1+0*4];
		final float b20 = b[2+0*4];
		final float b30 = b[3+0*4];
		final float b01 = b[0+1*4];
		final float b11 = b[1+1*4];
		final float b21 = b[2+1*4];
		final float b31 = b[3+1*4];
		final float b02 = b[0+2*4];
		final float b12 = b[1+2*4];
		final float b22 = b[2+2*4];
		final float b32 = b[3+2*4];
		final float b03 = b[0+3*4];
		final float b13 = b[1+3*4];
		final float b23 = b[2+3*4];
		final float b33 = b[3+3*4];

		float ai0=a[  0*4]; 
		float ai1=a[  1*4];
		float ai2=a[  2*4];
		float ai3=a[  3*4];
		result[ 0*4] = ai0 * b00  +  ai1 * b10  +  ai2 * b20  +  ai3 * b30 ;
		result[ 1*4] = ai0 * b01  +  ai1 * b11  +  ai2 * b21  +  ai3 * b31 ;
		result[ 2*4] = ai0 * b02  +  ai1 * b12  +  ai2 * b22  +  ai3 * b32 ;
		result[ 3*4] = ai0 * b03  +  ai1 * b13  +  ai2 * b23  +  ai3 * b33 ;

		ai0=a[1+0*4]; 
		ai1=a[1+1*4];
		ai2=a[1+2*4];
		ai3=a[1+3*4];
		result[1+0*4] = ai0 * b00  +  ai1 * b10  +  ai2 * b20  +  ai3 * b30 ;
		result[1+1*4] = ai0 * b01  +  ai1 * b11  +  ai2 * b21  +  ai3 * b31 ;
		result[1+2*4] = ai0 * b02  +  ai1 * b12  +  ai2 * b22  +  ai3 * b32 ;
		result[1+3*4] = ai0 * b03  +  ai1 * b13  +  ai2 * b23  +  ai3 * b33 ;

		ai0=a[2+0*4]; 
		ai1=a[2+1*4];
		ai2=a[2+2*4];
		ai3=a[2+3*4];
		result[2+0*4] = ai0 * b00  +  ai1 * b10  +  ai2 * b20  +  ai3 * b30 ;
		result[2+1*4] = ai0 * b01  +  ai1 * b11  +  ai2 * b21  +  ai3 * b31 ;
		result[2+2*4] = ai0 * b02  +  ai1 * b12  +  ai2 * b22  +  ai3 * b32 ;
		result[2+3*4] = ai0 * b03  +  ai1 * b13  +  ai2 * b23  +  ai3 * b33 ;

		ai0=a[3+0*4]; 
		ai1=a[3+1*4];
		ai2=a[3+2*4];
		ai3=a[3+3*4];
		result[3+0*4] = ai0 * b00  +  ai1 * b10  +  ai2 * b20  +  ai3 * b30 ;
		result[3+1*4] = ai0 * b01  +  ai1 * b11  +  ai2 * b21  +  ai3 * b31 ;
		result[3+2*4] = ai0 * b02  +  ai1 * b12  +  ai2 * b22  +  ai3 * b32 ;
		result[3+3*4] = ai0 * b03  +  ai1 * b13  +  ai2 * b23  +  ai3 * b33 ;

	}

	public static void orthoM(float[] m, 
			float left, float right, float bottom, float top,
			float near, float far) {

		final float r_width  = 1.0f / (right - left);
		final float r_height = 1.0f / (top - bottom);
		final float r_depth  = 1.0f / (far - near);
		final float x =  2.0f * (r_width);
		final float y =  2.0f * (r_height);
		final float z = -2.0f * (r_depth);
		final float tx = -(right + left) * r_width;
		final float ty = -(top + bottom) * r_height;
		final float tz = -(far + near) * r_depth;
		m[ 0] = x;
		m[ 5] = y;
		m[10] = z;
		m[12] = tx;
		m[13] = ty;
		m[14] = tz;
		m[15] = 1.0f;
		m[ 1] = 0.0f;
		m[ 2] = 0.0f;
		m[ 3] = 0.0f;
		m[ 4] = 0.0f;
		m[ 6] = 0.0f;
		m[ 7] = 0.0f;
		m[ 8] = 0.0f;
		m[ 9] = 0.0f;
		m[ 11] = 0.0f;
	}

	
	public static void scaleM(float[] m, float sx, float sy, float sz) {
		for (int i=0 ; i<4 ; i++) {
			m[     i] *= sx;
			m[ 4 + i] *= sy;
			m[ 8 + i] *= sz;
		}
	}
	
	public static void translateM( float[] m, float x, float y, float z) {
		for (int i=0 ; i<4 ; i++) {
			m[12 + i] += m[i] * x + m[4 + i] * y + m[8 + i] * z;
		}
	}

	
	public static void rotateM(float[] m, float a, float x, float y, float z) {
		float[] temp1 = new float[16];
		float[] temp2 = new float[16];
		setRotateM(temp1, a, x, y, z);
		multiplyMM(temp2,  m,  temp1);
		System.arraycopy(temp2, 0, m,0, 16);
	}

	
	public static void setRotateM(float[] rm, float a, float x, float y, float z) {
		rm[3] = 0;
		rm[7] = 0;
		rm[11]= 0;
		rm[12]= 0;
		rm[13]= 0;
		rm[14]= 0;
		rm[15]= 1;
		a *= (float) (Math.PI / 180.0f);
		float s = (float) Math.sin(a);
		float c = (float) Math.cos(a);
		if (1.0f == x && 0.0f == y && 0.0f == z) {
			rm[5] = c;   rm[10]= c;
			rm[6] = s;   rm[9] = -s;
			rm[1] = 0;   rm[2] = 0;
			rm[4] = 0;   rm[8] = 0;
			rm[0] = 1;
		} else if (0.0f == x && 1.0f == y && 0.0f == z) {
			rm[0] = c;   rm[10]= c;
			rm[8] = s;   rm[2] = -s;
			rm[1] = 0;   rm[4] = 0;
			rm[6] = 0;   rm[9] = 0;
			rm[5] = 1;
		} else if (0.0f == x && 0.0f == y && 1.0f == z) {
			rm[0] = c;   rm[5] = c;
			rm[1] = s;   rm[4] = -s;
			rm[2] = 0;   rm[6] = 0;
			rm[8] = 0;   rm[9] = 0;
			rm[10]= 1;
		} else {
			float len = length(x, y, z);
			if (1.0f != len) {
				float recipLen = 1.0f / len;
				x *= recipLen;
				y *= recipLen;
				z *= recipLen;
			}
			float nc = 1.0f - c;
			float xy = x * y;
			float yz = y * z;
			float zx = z * x;
			float xs = x * s;
			float ys = y * s;
			float zs = z * s;
			rm[ 0] = x*x*nc +  c;
			rm[ 4] =  xy*nc - zs;
			rm[ 8] =  zx*nc + ys;
			rm[ 1] =  xy*nc + zs;
			rm[ 5] = y*y*nc +  c;
			rm[ 9] =  yz*nc - xs;
			rm[ 2] =  zx*nc - ys;
			rm[ 6] =  yz*nc + xs;
			rm[10] = z*z*nc +  c;
		}
	}
	/**
	 * Computes the length of a vector.
	 *
	 * @param x x coordinate of a vector
	 * @param y y coordinate of a vector
	 * @param z z coordinate of a vector
	 * @return the length of a vector
	 */
	public static float length(float x, float y, float z) {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	
    public static void setLookAtM(float[] rm,
            float eyeX, float eyeY, float eyeZ,
            float centerX, float centerY, float centerZ, float upX, float upY,
            float upZ) {

     
        float fx = centerX - eyeX;
        float fy = centerY - eyeY;
        float fz = centerZ - eyeZ;

      
        float rlf = 1.0f / length(fx, fy, fz);
        fx *= rlf;
        fy *= rlf;
        fz *= rlf;

       
        float sx = fy * upZ - fz * upY;
        float sy = fz * upX - fx * upZ;
        float sz = fx * upY - fy * upX;

       
        float rls = 1.0f / length(sx, sy, sz);
        sx *= rls;
        sy *= rls;
        sz *= rls;

     
        float ux = sy * fz - sz * fy;
        float uy = sz * fx - sx * fz;
        float uz = sx * fy - sy * fx;

        rm[0] = sx;
        rm[1] = ux;
        rm[2] = -fx;
        rm[3] = 0.0f;

        rm[4] = sy;
        rm[5] = uy;
        rm[6] = -fy;
        rm[7] = 0.0f;

        rm[8] = sz;
        rm[9] = uz;
        rm[10] = -fz;
        rm[11] = 0.0f;

        rm[12] = 0.0f;
        rm[13] = 0.0f;
        rm[14] = 0.0f;
        rm[15] = 1.0f;

        translateM(rm,  -eyeX, -eyeY, -eyeZ);
    }
    
 
    public static void multiplyMV(float[] v_out, float[] m_in, float[] v_in) {
       
        v_out[0] = v_in[0] * m_in[0*4  ]  +  v_in[1] * m_in[1*4  ] +
                   v_in[2] * m_in[2*4  ]  +  v_in[3] * m_in[3*4  ];

        v_out[1] = v_in[0] * m_in[0*4+1]  +  v_in[1] * m_in[1*4+1] +
                   v_in[2] * m_in[2*4+1]  +  v_in[3] * m_in[3*4+1];

        v_out[2] = v_in[0] * m_in[0*4+2]  +  v_in[1] * m_in[1*4+2] +
                   v_in[2] * m_in[2*4+2]  +  v_in[3] * m_in[3*4+2];

        v_out[3] = v_in[0] * m_in[0*4+3]  +  v_in[1] * m_in[1*4+3] +
                   v_in[2] * m_in[2*4+3]  +  v_in[3] * m_in[3*4+3];

    }
   
}
