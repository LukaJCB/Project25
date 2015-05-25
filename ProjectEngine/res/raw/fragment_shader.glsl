precision mediump float;

uniform sampler2D uTexture;

varying vec2 vTexCoordinate;

void main(){
	gl_FragColor = vec4(1.0,0.0,0.0,0.0)*texture2D(uTexture, vTexCoordinate);
}