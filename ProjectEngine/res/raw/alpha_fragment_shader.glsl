precision mediump float;

uniform sampler2D uTexture;

varying vec2 vTexCoordinate;

void main(){
	vec4 frag = vec4(1.0,0.0,0.0,1.0);
	//*texture2D(uTexture, vTexCoordinate);
	if (frag.a > 0.5){ 
		gl_FragColor = frag;
	} else {
		discard;
	}
}