uniform vec3 uColor;
uniform float uCurrentTime;

varying float vTime;

void main(){
	gl_FragColor = vec4(uColor / (vTime * 0.01) ,1.0);
}