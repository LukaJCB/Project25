precision mediump float;
uniform vec3 uColor;

varying float vTime;

void main(){
	gl_FragColor = vec4(uColor / (vTime * 0.01) ,1.0);
}