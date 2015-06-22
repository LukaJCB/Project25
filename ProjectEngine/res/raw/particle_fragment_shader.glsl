uniform vec3 uColor;

varying vTime;

void main(){
	gl_FragColor = vec4(uColor / vTime,1.0);
}