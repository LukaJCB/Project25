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

	public static boolean invertM(float[] mInv, float[] m) {

		// Invert a 4 x 4 matrix using Cramer's Rule

		// transpose matrix
		final float src0  = m[  0];
		final float src4  = m[  1];
		final float src8  = m[  2];
		final float src12 = m[  3];

		final float src1  = m[  4];
		final float src5  = m[  5];
		final float src9  = m[  6];
		final float src13 = m[  7];

		final float src2  = m[  8];
		final float src6  = m[  9];
		final float src10 = m[ 10];
		final float src14 = m[ 11];

		final float src3  = m[ 12];
		final float src7  = m[ 13];
		final float src11 = m[ 14];
		final float src15 = m[ 15];

		// calculate pairs for first 8 elements (cofactors)
		final float atmp0  = src10 * src15;
		final float atmp1  = src11 * src14;
		final float atmp2  = src9  * src15;
		final float atmp3  = src11 * src13;
		final float atmp4  = src9  * src14;
		final float atmp5  = src10 * src13;
		final float atmp6  = src8  * src15;
		final float atmp7  = src11 * src12;
		final float atmp8  = src8  * src14;
		final float atmp9  = src10 * src12;
		final float atmp10 = src8  * src13;
		final float atmp11 = src9  * src12;

		// calculate first 8 elements (cofactors)
		final float dst0  = (atmp0 * src5 + atmp3 * src6 + atmp4  * src7)
				- (atmp1 * src5 + atmp2 * src6 + atmp5  * src7);
		final float dst1  = (atmp1 * src4 + atmp6 * src6 + atmp9  * src7)
				- (atmp0 * src4 + atmp7 * src6 + atmp8  * src7);
		final float dst2  = (atmp2 * src4 + atmp7 * src5 + atmp10 * src7)
				- (atmp3 * src4 + atmp6 * src5 + atmp11 * src7);
		final float dst3  = (atmp5 * src4 + atmp8 * src5 + atmp11 * src6)
				- (atmp4 * src4 + atmp9 * src5 + atmp10 * src6);
		final float dst4  = (atmp1 * src1 + atmp2 * src2 + atmp5  * src3)
				- (atmp0 * src1 + atmp3 * src2 + atmp4  * src3);
		final float dst5  = (atmp0 * src0 + atmp7 * src2 + atmp8  * src3)
				- (atmp1 * src0 + atmp6 * src2 + atmp9  * src3);
		final float dst6  = (atmp3 * src0 + atmp6 * src1 + atmp11 * src3)
				- (atmp2 * src0 + atmp7 * src1 + atmp10 * src3);
		final float dst7  = (atmp4 * src0 + atmp9 * src1 + atmp10 * src2)
				- (atmp5 * src0 + atmp8 * src1 + atmp11 * src2);

		// calculate pairs for second 8 elements (cofactors)
		final float btmp0  = src2 * src7;
		final float btmp1  = src3 * src6;
		final float btmp2  = src1 * src7;
		final float btmp3  = src3 * src5;
		final float btmp4  = src1 * src6;
		final float btmp5  = src2 * src5;
		final float btmp6  = src0 * src7;
		final float btmp7  = src3 * src4;
		final float btmp8  = src0 * src6;
		final float btmp9  = src2 * src4;
		final float btmp10 = src0 * src5;
		final float btmp11 = src1 * src4;

		// calculate second 8 elements (cofactors)
		final float dst8  = (btmp0  * src13 + btmp3  * src14 + btmp4  * src15)
				- (btmp1  * src13 + btmp2  * src14 + btmp5  * src15);
		final float dst9  = (btmp1  * src12 + btmp6  * src14 + btmp9  * src15)
				- (btmp0  * src12 + btmp7  * src14 + btmp8  * src15);
		final float dst10 = (btmp2  * src12 + btmp7  * src13 + btmp10 * src15)
				- (btmp3  * src12 + btmp6  * src13 + btmp11 * src15);
		final float dst11 = (btmp5  * src12 + btmp8  * src13 + btmp11 * src14)
				- (btmp4  * src12 + btmp9  * src13 + btmp10 * src14);
		final float dst12 = (btmp2  * src10 + btmp5  * src11 + btmp1  * src9 )
				- (btmp4  * src11 + btmp0  * src9  + btmp3  * src10);
		final float dst13 = (btmp8  * src11 + btmp0  * src8  + btmp7  * src10)
				- (btmp6  * src10 + btmp9  * src11 + btmp1  * src8 );
		final float dst14 = (btmp6  * src9  + btmp11 * src11 + btmp3  * src8 )
				- (btmp10 * src11 + btmp2  * src8  + btmp7  * src9 );
		final float dst15 = (btmp10 * src10 + btmp4  * src8  + btmp9  * src9 )
				- (btmp8  * src9  + btmp11 * src10 + btmp5  * src8 );

		// calculate determinant
		final float det =
				src0 * dst0 + src1 * dst1 + src2 * dst2 + src3 * dst3;

		if (det == 0.0f) {
			return false;
		}

		// calculate matrix inverse
		final float invdet = 1.0f / det;
		mInv[0] = dst0  * invdet;
		mInv[ 1] = dst1  * invdet;
		mInv[ 2] = dst2  * invdet;
		mInv[ 3] = dst3  * invdet;

		mInv[ 4] = dst4  * invdet;
		mInv[ 5] = dst5  * invdet;
		mInv[ 6] = dst6  * invdet;
		mInv[ 7] = dst7  * invdet;

		mInv[ 8] = dst8  * invdet;
		mInv[ 9] = dst9  * invdet;
		mInv[10] = dst10 * invdet;
		mInv[11] = dst11 * invdet;

		mInv[12] = dst12 * invdet;
		mInv[13] = dst13 * invdet;
		mInv[14] = dst14 * invdet;
		mInv[15] = dst15 * invdet;

		return true;
	}



}
