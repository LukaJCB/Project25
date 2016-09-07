uniform mat4 uMatrix;

attribute vec4 aPosition;
attribute vec2 aTexCoordinates;

varying vec2 vTexCoordinate; 

void main(){
	vTexCoordinate = aTexCoordinates;
	gl_Position = uMatrix * aPosition;
	
}