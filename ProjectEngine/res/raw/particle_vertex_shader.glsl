uniform mat4 uMatrix;
uniform float uTime;

attribute vec3 aPosition;
attribute vec3 aDirection;
attribute float aParticleStartTime;

varying float vTime;

void main(){
	vTime = uTime - aParticleStartTime;
	vec3 currentPosition = aPosition + (aDirectionVector * vElapsedTime);
	gl_Position = uMatrix * vec4(currentPosition, 1.0);
	gl_PointSize = 10.0;
}