uniform mat4 uMatrix;
uniform float uCurrentTime;
uniform vec3 uPosition;

attribute vec3 aDirection;
attribute float aParticleStartTime;

varying float vTime;

void main(){
	vTime = uCurrentTime - aParticleStartTime;
	vec3 currentPosition = uPosition + (aDirection * vTime);
	gl_Position = uMatrix * vec4(currentPosition, 1.0);
}