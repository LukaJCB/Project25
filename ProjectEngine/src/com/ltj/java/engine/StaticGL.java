package com.ltj.java.engine;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import com.jogamp.common.nio.PointerBuffer;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES1;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL2ES3;
import com.jogamp.opengl.GL2GL3;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GL3ES3;
import com.jogamp.opengl.GL3bc;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GL4ES3;
import com.jogamp.opengl.GL4bc;
import com.jogamp.opengl.GLArrayData;
import com.jogamp.opengl.GLBufferStorage;
import com.jogamp.opengl.GLContext;
import com.jogamp.opengl.GLES1;
import com.jogamp.opengl.GLES2;
import com.jogamp.opengl.GLES3;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLUniformData;

public abstract class StaticGL {

	private static GL3 gl;

	public static void insertGL(GL3 gl){
		StaticGL.gl = gl;
	}

	public static boolean isGL() {
		return gl.isGL();
	}

	public static boolean isGL4bc() {
		return gl.isGL4bc();
	}

	public static boolean isGL4() {
		return gl.isGL4();
	}

	public static boolean isGL3bc() {
		return gl.isGL3bc();
	}

	public static boolean isGL3() {
		return gl.isGL3();
	}

	public static boolean isGL2() {
		return gl.isGL2();
	}

	public static boolean isGLES1() {
		return gl.isGLES1();
	}

	public static boolean isGLES2() {
		return gl.isGLES2();
	}

	public static boolean isGLES3() {
		return gl.isGLES3();
	}

	public static boolean isGLES() {
		return gl.isGLES();
	}

	public static boolean isGL2ES1() {
		return gl.isGL2ES1();
	}

	public static boolean isGL2ES2() {
		return gl.isGL2ES2();
	}

	public static boolean isGL2ES3() {
		return gl.isGL2ES3();
	}

	public static boolean isGL3ES3() {
		return gl.isGL3ES3();
	}

	public static boolean isGL4ES3() {
		return gl.isGL4ES3();
	}

	public static boolean isGL2GL3() {
		return gl.isGL2GL3();
	}

	public static boolean isGL4core() {
		return gl.isGL4core();
	}

	public static boolean isGL3core() {
		return gl.isGL3core();
	}

	public static boolean isGLcore() {
		return gl.isGLcore();
	}

	public static boolean isGLES2Compatible() {
		return gl.isGLES2Compatible();
	}

	public static boolean isGLES3Compatible() {
		return gl.isGLES3Compatible();
	}

	public static boolean isGLES31Compatible() {
		return gl.isGLES31Compatible();
	}

	public static boolean hasGLSL() {
		return gl.hasGLSL();
	}

	public static GL getDownstreamGL() throws GLException {
		return gl.getDownstreamGL();
	}

	public static GL getRootGL() throws GLException {
		return gl.getRootGL();
	}

	public static GL getGL() throws GLException {
		return gl.getGL();
	}

	public static GL4bc getGL4bc() throws GLException {
		return gl.getGL4bc();
	}

	public static GL4 getGL4() throws GLException {
		return gl.getGL4();
	}

	public static GL3bc getGL3bc() throws GLException {
		return gl.getGL3bc();
	}

	public static GL3 getGL3() throws GLException {
		return gl.getGL3();
	}

	public static GL2 getGL2() throws GLException {
		return gl.getGL2();
	}

	public static GLES1 getGLES1() throws GLException {
		return gl.getGLES1();
	}

	public static GLES2 getGLES2() throws GLException {
		return gl.getGLES2();
	}

	public static GLES3 getGLES3() throws GLException {
		return gl.getGLES3();
	}

	public static GL2ES1 getGL2ES1() throws GLException {
		return gl.getGL2ES1();
	}

	public static GL2ES2 getGL2ES2() throws GLException {
		return gl.getGL2ES2();
	}

	public static GL2ES3 getGL2ES3() throws GLException {
		return gl.getGL2ES3();
	}

	public static GL3ES3 getGL3ES3() throws GLException {
		return gl.getGL3ES3();
	}

	public static GL4ES3 getGL4ES3() throws GLException {
		return gl.getGL4ES3();
	}

	public static GL2GL3 getGL2GL3() throws GLException {
		return gl.getGL2GL3();
	}

	public static GLProfile getGLProfile() {
		return gl.getGLProfile();
	}

	public static GLContext getContext() {
		return gl.getContext();
	}

	public static boolean isFunctionAvailable(String glFunctionName) {
		return gl.isFunctionAvailable(glFunctionName);
	}

	public static boolean isExtensionAvailable(String glExtensionName) {
		return gl.isExtensionAvailable(glExtensionName);
	}

	public static boolean hasBasicFBOSupport() {
		return gl.hasBasicFBOSupport();
	}

	public static boolean hasFullFBOSupport() {
		return gl.hasFullFBOSupport();
	}

	public static int getMaxRenderbufferSamples() {
		return gl.getMaxRenderbufferSamples();
	}

	public static boolean isNPOTTextureAvailable() {
		return gl.isNPOTTextureAvailable();
	}

	public static boolean isTextureFormatBGRA8888Available() {
		return gl.isTextureFormatBGRA8888Available();
	}

	public static void setSwapInterval(int interval) {
		gl.setSwapInterval(interval);
	}

	public static int getSwapInterval() {
		return gl.getSwapInterval();
	}

	public static Object getPlatformGLExtensions() {
		return gl.getPlatformGLExtensions();
	}

	public static Object getExtension(String extensionName) {
		return gl.getExtension(extensionName);
	}

	public static void glBindSampler(int unit, int sampler) {
		gl.glBindSampler(unit, sampler);
	}

	public static void glBindVertexBuffer(int bindingindex, int buffer, long offset,
			int stride) {
		gl.glBindVertexBuffer(bindingindex, buffer, offset, stride);
	}

	public static int glClientWaitSync(long sync, int flags, long timeout) {
		return gl.glClientWaitSync(sync, flags, timeout);
	}

	public static int getBoundBuffer(int target) {
		return gl.getBoundBuffer(target);
	}

	public static void glDeleteSamplers(int count, IntBuffer samplers) {
		gl.glDeleteSamplers(count, samplers);
	}

	public static GLBufferStorage getBufferStorage(int bufferName) {
		return gl.getBufferStorage(bufferName);
	}

	public static void glDeleteSamplers(int count, int[] samplers, int samplers_offset) {
		gl.glDeleteSamplers(count, samplers, samplers_offset);
	}

	public static GLBufferStorage mapBuffer(int target, int access) throws GLException {
		return gl.mapBuffer(target, access);
	}

	public static void glDeleteSync(long sync) {
		gl.glDeleteSync(sync);
	}

	public static void glDispatchCompute(int num_groups_x, int num_groups_y,
			int num_groups_z) {
		gl.glDispatchCompute(num_groups_x, num_groups_y, num_groups_z);
	}

	public static void glDispatchComputeIndirect(long indirect) {
		gl.glDispatchComputeIndirect(indirect);
	}

	public static void glDrawArraysIndirect(int mode, Buffer indirect) {
		gl.glDrawArraysIndirect(mode, indirect);
	}

	public static GLBufferStorage mapBufferRange(int target, long offset, long length,
			int access) throws GLException {
		return gl.mapBufferRange(target, offset, length, access);
	}

	public static void glDrawArraysIndirect(int mode, long indirect_buffer_offset) {
		gl.glDrawArraysIndirect(mode, indirect_buffer_offset);
	}

	public static void glDrawElementsIndirect(int mode, int type, Buffer indirect) {
		gl.glDrawElementsIndirect(mode, type, indirect);
	}

	public static void glDrawElementsIndirect(int mode, int type,
			long indirect_buffer_offset) {
		gl.glDrawElementsIndirect(mode, type, indirect_buffer_offset);
	}

	public static long glFenceSync(int condition, int flags) {
		return gl.glFenceSync(condition, flags);
	}

	public static boolean isVBOArrayBound() {
		return gl.isVBOArrayBound();
	}

	public static boolean isVBOElementArrayBound() {
		return gl.isVBOElementArrayBound();
	}

	public static void glGenSamplers(int count, IntBuffer samplers) {
		gl.glGenSamplers(count, samplers);
	}

	public static int getBoundFramebuffer(int target) {
		return gl.getBoundFramebuffer(target);
	}

	public static int getDefaultDrawFramebuffer() {
		return gl.getDefaultDrawFramebuffer();
	}

	public static void glGenSamplers(int count, int[] samplers, int samplers_offset) {
		gl.glGenSamplers(count, samplers, samplers_offset);
	}

	public static void glGetBufferParameteri64v(int target, int pname,
			LongBuffer params) {
		gl.glGetBufferParameteri64v(target, pname, params);
	}

	public static int getDefaultReadFramebuffer() {
		return gl.getDefaultReadFramebuffer();
	}

	public static int getDefaultReadBuffer() {
		return gl.getDefaultReadBuffer();
	}

	public static void glGetBufferParameteri64v(int target, int pname, long[] params,
			int params_offset) {
		gl.glGetBufferParameteri64v(target, pname, params, params_offset);
	}

	public static void glGetInteger64i_v(int target, int index, LongBuffer data) {
		gl.glGetInteger64i_v(target, index, data);
	}

	public static void glGetInteger64i_v(int target, int index, long[] data,
			int data_offset) {
		gl.glGetInteger64i_v(target, index, data, data_offset);
	}

	public static void glGetInteger64v(int pname, LongBuffer data) {
		gl.glGetInteger64v(pname, data);
	}

	public static void glGetInteger64v(int pname, long[] data, int data_offset) {
		gl.glGetInteger64v(pname, data, data_offset);
	}

	public static void glGetProgramInterfaceiv(int program, int programInterface,
			int pname, IntBuffer params) {
		gl.glGetProgramInterfaceiv(program, programInterface, pname, params);
	}

	public static void glGetProgramInterfaceiv(int program, int programInterface,
			int pname, int[] params, int params_offset) {
		gl.glGetProgramInterfaceiv(program, programInterface, pname, params,
				params_offset);
	}

	public static int glGetProgramResourceIndex(int program, int programInterface,
			ByteBuffer name) {
		return gl.glGetProgramResourceIndex(program, programInterface, name);
	}

	public static int glGetProgramResourceIndex(int program, int programInterface,
			byte[] name, int name_offset) {
		return gl.glGetProgramResourceIndex(program, programInterface, name,
				name_offset);
	}

	public static int glGetProgramResourceLocation(int program, int programInterface,
			ByteBuffer name) {
		return gl.glGetProgramResourceLocation(program, programInterface, name);
	}

	public static int glGetProgramResourceLocation(int program, int programInterface,
			byte[] name, int name_offset) {
		return gl.glGetProgramResourceLocation(program, programInterface, name,
				name_offset);
	}

	public static void glGetProgramResourceName(int program, int programInterface,
			int index, int bufSize, IntBuffer length, ByteBuffer name) {
		gl.glGetProgramResourceName(program, programInterface, index, bufSize,
				length, name);
	}

	public static void glGetProgramResourceName(int program, int programInterface,
			int index, int bufSize, int[] length, int length_offset,
			byte[] name, int name_offset) {
		gl.glGetProgramResourceName(program, programInterface, index, bufSize,
				length, length_offset, name, name_offset);
	}

	public static void glGetProgramResourceiv(int program, int programInterface,
			int index, int propCount, IntBuffer props, int bufSize,
			IntBuffer length, IntBuffer params) {
		gl.glGetProgramResourceiv(program, programInterface, index, propCount,
				props, bufSize, length, params);
	}

	public static void glGetProgramResourceiv(int program, int programInterface,
			int index, int propCount, int[] props, int props_offset,
			int bufSize, int[] length, int length_offset, int[] params,
			int params_offset) {
		gl.glGetProgramResourceiv(program, programInterface, index, propCount,
				props, props_offset, bufSize, length, length_offset, params,
				params_offset);
	}

	public static void glBindFragDataLocationIndexed(int program, int colorNumber,
			int index, String name) {
		gl.glBindFragDataLocationIndexed(program, colorNumber, index, name);
	}

	public static void glGetSamplerParameterfv(int sampler, int pname,
			FloatBuffer params) {
		gl.glGetSamplerParameterfv(sampler, pname, params);
	}

	public static void glCompileShaderIncludeARB(int shader, int count, String[] path,
			IntBuffer length) {
		gl.glCompileShaderIncludeARB(shader, count, path, length);
	}

	public static void glGetSamplerParameterfv(int sampler, int pname, float[] params,
			int params_offset) {
		gl.glGetSamplerParameterfv(sampler, pname, params, params_offset);
	}

	public static void glCompileShaderIncludeARB(int shader, int count, String[] path,
			int[] length, int length_offset) {
		gl.glCompileShaderIncludeARB(shader, count, path, length, length_offset);
	}

	public static void glGetSamplerParameteriv(int sampler, int pname, IntBuffer params) {
		gl.glGetSamplerParameteriv(sampler, pname, params);
	}

	public static long glCreateSyncFromCLeventARB(long context, long event, int flags) {
		return gl.glCreateSyncFromCLeventARB(context, event, flags);
	}

	public static void glGetSamplerParameteriv(int sampler, int pname, int[] params,
			int params_offset) {
		gl.glGetSamplerParameteriv(sampler, pname, params, params_offset);
	}

	public static void glDeleteNamedStringARB(int namelen, String name) {
		gl.glDeleteNamedStringARB(namelen, name);
	}

	public static void glDepthRangeArrayv(int first, int count, DoubleBuffer v) {
		gl.glDepthRangeArrayv(first, count, v);
	}

	public static void glGetSynciv(long sync, int pname, int bufSize,
			IntBuffer length, IntBuffer values) {
		gl.glGetSynciv(sync, pname, bufSize, length, values);
	}

	public static void glDepthRangeArrayv(int first, int count, double[] v,
			int v_offset) {
		gl.glDepthRangeArrayv(first, count, v, v_offset);
	}

	public static void glGetSynciv(long sync, int pname, int bufSize, int[] length,
			int length_offset, int[] values, int values_offset) {
		gl.glGetSynciv(sync, pname, bufSize, length, length_offset, values,
				values_offset);
	}

	public static void glDepthRangeIndexed(int index, double n, double f) {
		gl.glDepthRangeIndexed(index, n, f);
	}

	public static void glDrawElementsBaseVertex(int mode, int count, int type,
			long indices_buffer_offset, int basevertex) {
		gl.glDrawElementsBaseVertex(mode, count, type, indices_buffer_offset,
				basevertex);
	}

	public static boolean glIsSampler(int sampler) {
		return gl.glIsSampler(sampler);
	}

	public static boolean glIsSync(long sync) {
		return gl.glIsSync(sync);
	}

	public static void glDrawElementsInstancedBaseVertex(int mode, int count,
			int type, long indices_buffer_offset, int instancecount,
			int basevertex) {
		gl.glDrawElementsInstancedBaseVertex(mode, count, type,
				indices_buffer_offset, instancecount, basevertex);
	}

	public static void glMemoryBarrierByRegion(int barriers) {
		gl.glMemoryBarrierByRegion(barriers);
	}

	public static void glSamplerParameterf(int sampler, int pname, float param) {
		gl.glSamplerParameterf(sampler, pname, param);
	}

	public static void glDrawRangeElementsBaseVertex(int mode, int start, int end,
			int count, int type, long indices_buffer_offset, int basevertex) {
		gl.glDrawRangeElementsBaseVertex(mode, start, end, count, type,
				indices_buffer_offset, basevertex);
	}

	public static void glSamplerParameterfv(int sampler, int pname, FloatBuffer param) {
		gl.glSamplerParameterfv(sampler, pname, param);
	}

	public static void glSamplerParameterfv(int sampler, int pname, float[] param,
			int param_offset) {
		gl.glSamplerParameterfv(sampler, pname, param, param_offset);
	}

	public static void glDrawTransformFeedbackInstanced(int mode, int id,
			int instancecount) {
		gl.glDrawTransformFeedbackInstanced(mode, id, instancecount);
	}

	public static void glSamplerParameteri(int sampler, int pname, int param) {
		gl.glSamplerParameteri(sampler, pname, param);
	}

	public static void glDrawTransformFeedbackStreamInstanced(int mode, int id,
			int stream, int instancecount) {
		gl.glDrawTransformFeedbackStreamInstanced(mode, id, stream,
				instancecount);
	}

	public static void glSamplerParameteriv(int sampler, int pname, IntBuffer param) {
		gl.glSamplerParameteriv(sampler, pname, param);
	}

	public static void glFramebufferTexture(int target, int attachment, int texture,
			int level) {
		gl.glFramebufferTexture(target, attachment, texture, level);
	}

	public static void glSamplerParameteriv(int sampler, int pname, int[] param,
			int param_offset) {
		gl.glSamplerParameteriv(sampler, pname, param, param_offset);
	}

	public static void glFramebufferTextureARB(int target, int attachment,
			int texture, int level) {
		gl.glFramebufferTextureARB(target, attachment, texture, level);
	}

	public static void glVertexAttribBinding(int attribindex, int bindingindex) {
		gl.glVertexAttribBinding(attribindex, bindingindex);
	}

	public static void glFramebufferTextureFaceARB(int target, int attachment,
			int texture, int level, int face) {
		gl.glFramebufferTextureFaceARB(target, attachment, texture, level, face);
	}

	public static void glVertexAttribFormat(int attribindex, int size, int type,
			boolean normalized, int relativeoffset) {
		gl.glVertexAttribFormat(attribindex, size, type, normalized,
				relativeoffset);
	}

	public static void glFramebufferTextureLayerARB(int target, int attachment,
			int texture, int level, int layer) {
		gl.glFramebufferTextureLayerARB(target, attachment, texture, level,
				layer);
	}

	public static void glVertexAttribIFormat(int attribindex, int size, int type,
			int relativeoffset) {
		gl.glVertexAttribIFormat(attribindex, size, type, relativeoffset);
	}

	public static void glGetActiveSubroutineName(int program, int shadertype,
			int index, int bufsize, IntBuffer length, ByteBuffer name) {
		gl.glGetActiveSubroutineName(program, shadertype, index, bufsize,
				length, name);
	}

	public static void glVertexBindingDivisor(int bindingindex, int divisor) {
		gl.glVertexBindingDivisor(bindingindex, divisor);
	}

	public static void glGetActiveSubroutineName(int program, int shadertype,
			int index, int bufsize, int[] length, int length_offset,
			byte[] name, int name_offset) {
		gl.glGetActiveSubroutineName(program, shadertype, index, bufsize,
				length, length_offset, name, name_offset);
	}

	public static void glWaitSync(long sync, int flags, long timeout) {
		gl.glWaitSync(sync, flags, timeout);
	}

	public static void glGetActiveSubroutineUniformName(int program, int shadertype,
			int index, int bufsize, IntBuffer length, ByteBuffer name) {
		gl.glGetActiveSubroutineUniformName(program, shadertype, index,
				bufsize, length, name);
	}

	public static void glGetActiveSubroutineUniformName(int program, int shadertype,
			int index, int bufsize, int[] length, int length_offset,
			byte[] name, int name_offset) {
		gl.glGetActiveSubroutineUniformName(program, shadertype, index,
				bufsize, length, length_offset, name, name_offset);
	}

	public static void glGetActiveSubroutineUniformiv(int program, int shadertype,
			int index, int pname, IntBuffer values) {
		gl.glGetActiveSubroutineUniformiv(program, shadertype, index, pname,
				values);
	}

	public static void glGetActiveSubroutineUniformiv(int program, int shadertype,
			int index, int pname, int[] values, int values_offset) {
		gl.glGetActiveSubroutineUniformiv(program, shadertype, index, pname,
				values, values_offset);
	}

	public static void glGetDoublei_v(int target, int index, DoubleBuffer data) {
		gl.glGetDoublei_v(target, index, data);
	}

	public static void glGetDoublei_v(int target, int index, double[] data,
			int data_offset) {
		gl.glGetDoublei_v(target, index, data, data_offset);
	}

	public static void glGetFloati_v(int target, int index, FloatBuffer data) {
		gl.glGetFloati_v(target, index, data);
	}

	public static void glGetFloati_v(int target, int index, float[] data,
			int data_offset) {
		gl.glGetFloati_v(target, index, data, data_offset);
	}

	public static int glGetFragDataIndex(int program, String name) {
		return gl.glGetFragDataIndex(program, name);
	}

	public static void glGetNamedStringARB(int namelen, String name, int bufSize,
			IntBuffer stringlen, ByteBuffer string) {
		gl.glGetNamedStringARB(namelen, name, bufSize, stringlen, string);
	}

	public static void glGetNamedStringARB(int namelen, String name, int bufSize,
			int[] stringlen, int stringlen_offset, byte[] string,
			int string_offset) {
		gl.glGetNamedStringARB(namelen, name, bufSize, stringlen,
				stringlen_offset, string, string_offset);
	}

	public static void glGetNamedStringivARB(int namelen, String name, int pname,
			IntBuffer params) {
		gl.glGetNamedStringivARB(namelen, name, pname, params);
	}

	public static void glGetNamedStringivARB(int namelen, String name, int pname,
			int[] params, int params_offset) {
		gl.glGetNamedStringivARB(namelen, name, pname, params, params_offset);
	}

	public static void glGetProgramStageiv(int program, int shadertype, int pname,
			IntBuffer values) {
		gl.glGetProgramStageiv(program, shadertype, pname, values);
	}

	public static void glGetProgramStageiv(int program, int shadertype, int pname,
			int[] values, int values_offset) {
		gl.glGetProgramStageiv(program, shadertype, pname, values,
				values_offset);
	}

	public static int glGetSubroutineIndex(int program, int shadertype, String name) {
		return gl.glGetSubroutineIndex(program, shadertype, name);
	}

	public static int glGetSubroutineUniformLocation(int program, int shadertype,
			String name) {
		return gl.glGetSubroutineUniformLocation(program, shadertype, name);
	}

	public static void glGetUniformSubroutineuiv(int shadertype, int location,
			IntBuffer params) {
		gl.glGetUniformSubroutineuiv(shadertype, location, params);
	}

	public static void glGetUniformSubroutineuiv(int shadertype, int location,
			int[] params, int params_offset) {
		gl.glGetUniformSubroutineuiv(shadertype, location, params,
				params_offset);
	}

	public static void glGetUniformdv(int program, int location, DoubleBuffer params) {
		gl.glGetUniformdv(program, location, params);
	}

	public static void glGetUniformdv(int program, int location, double[] params,
			int params_offset) {
		gl.glGetUniformdv(program, location, params, params_offset);
	}

	public static boolean glIsNamedStringARB(int namelen, String name) {
		return gl.glIsNamedStringARB(namelen, name);
	}

	public static void glMultiDrawArraysIndirect(int mode,
			long indirect_buffer_offset, int drawcount, int stride) {
		gl.glMultiDrawArraysIndirect(mode, indirect_buffer_offset, drawcount,
				stride);
	}

	public static void glMultiDrawElementsBaseVertex(int mode, IntBuffer count,
			int type, PointerBuffer indices, int drawcount, IntBuffer basevertex) {
		gl.glMultiDrawElementsBaseVertex(mode, count, type, indices, drawcount,
				basevertex);
	}

	public static void glMultiDrawElementsIndirect(int mode, int type,
			Buffer indirect, int drawcount, int stride) {
		gl.glMultiDrawElementsIndirect(mode, type, indirect, drawcount, stride);
	}

	public static void glNamedStringARB(int type, int namelen, String name,
			int stringlen, String string) {
		gl.glNamedStringARB(type, namelen, name, stringlen, string);
	}

	public static void glPatchParameterfv(int pname, FloatBuffer values) {
		gl.glPatchParameterfv(pname, values);
	}

	public static void glPatchParameterfv(int pname, float[] values, int values_offset) {
		gl.glPatchParameterfv(pname, values, values_offset);
	}

	public static void glPatchParameteri(int pname, int value) {
		gl.glPatchParameteri(pname, value);
	}

	public static void glProgramParameteriARB(int program, int pname, int value) {
		gl.glProgramParameteriARB(program, pname, value);
	}

	public static void glScissorArrayv(int first, int count, IntBuffer v) {
		gl.glScissorArrayv(first, count, v);
	}

	public static void glScissorArrayv(int first, int count, int[] v, int v_offset) {
		gl.glScissorArrayv(first, count, v, v_offset);
	}

	public static void glScissorIndexed(int index, int left, int bottom, int width,
			int height) {
		gl.glScissorIndexed(index, left, bottom, width, height);
	}

	public static void glScissorIndexedv(int index, IntBuffer v) {
		gl.glScissorIndexedv(index, v);
	}

	public static void glScissorIndexedv(int index, int[] v, int v_offset) {
		gl.glScissorIndexedv(index, v, v_offset);
	}

	public static void glTexBufferRange(int target, int internalformat, int buffer,
			long offset, long size) {
		gl.glTexBufferRange(target, internalformat, buffer, offset, size);
	}

	public static void glUniform1d(int location, double x) {
		gl.glUniform1d(location, x);
	}

	public static void glUniform1dv(int location, int count, DoubleBuffer value) {
		gl.glUniform1dv(location, count, value);
	}

	public static void glUniform1dv(int location, int count, double[] value,
			int value_offset) {
		gl.glUniform1dv(location, count, value, value_offset);
	}

	public static void glUniform2d(int location, double x, double y) {
		gl.glUniform2d(location, x, y);
	}

	public static void glUniform2dv(int location, int count, DoubleBuffer value) {
		gl.glUniform2dv(location, count, value);
	}

	public static void glUniform2dv(int location, int count, double[] value,
			int value_offset) {
		gl.glUniform2dv(location, count, value, value_offset);
	}

	public static void glActiveShaderProgram(int pipeline, int program) {
		gl.glActiveShaderProgram(pipeline, program);
	}

	public static void glUniform3d(int location, double x, double y, double z) {
		gl.glUniform3d(location, x, y, z);
	}

	public static void glAttachShader(int program, int shader) {
		gl.glAttachShader(program, shader);
	}

	public static void glUniform3dv(int location, int count, DoubleBuffer value) {
		gl.glUniform3dv(location, count, value);
	}

	public static void glBeginQuery(int target, int id) {
		gl.glBeginQuery(target, id);
	}

	public static void glUniform3dv(int location, int count, double[] value,
			int value_offset) {
		gl.glUniform3dv(location, count, value, value_offset);
	}

	public static void glBindAttribLocation(int program, int index, String name) {
		gl.glBindAttribLocation(program, index, name);
	}

	public static void glUniform4d(int location, double x, double y, double z, double w) {
		gl.glUniform4d(location, x, y, z, w);
	}

	public static void glBindProgramPipeline(int pipeline) {
		gl.glBindProgramPipeline(pipeline);
	}

	public static void glUniform4dv(int location, int count, DoubleBuffer value) {
		gl.glUniform4dv(location, count, value);
	}

	public static void glBlendColor(float red, float green, float blue, float alpha) {
		gl.glBlendColor(red, green, blue, alpha);
	}

	public static void glUniform4dv(int location, int count, double[] value,
			int value_offset) {
		gl.glUniform4dv(location, count, value, value_offset);
	}

	public static void glCompileShader(int shader) {
		gl.glCompileShader(shader);
	}

	public static void glUniformMatrix2dv(int location, int count, boolean transpose,
			DoubleBuffer value) {
		gl.glUniformMatrix2dv(location, count, transpose, value);
	}

	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, Buffer data) {
		gl.glCompressedTexImage3D(target, level, internalformat, width, height,
				depth, border, imageSize, data);
	}

	public static void glUniformMatrix2dv(int location, int count, boolean transpose,
			double[] value, int value_offset) {
		gl.glUniformMatrix2dv(location, count, transpose, value, value_offset);
	}

	public static void glUniformMatrix2x3dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix2x3dv(location, count, transpose, value);
	}

	public static void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, long data_buffer_offset) {
		gl.glCompressedTexImage3D(target, level, internalformat, width, height,
				depth, border, imageSize, data_buffer_offset);
	}

	public static void glUniformMatrix2x3dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix2x3dv(location, count, transpose, value, value_offset);
	}

	public static void glUniformMatrix2x4dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix2x4dv(location, count, transpose, value);
	}

	public static void glCompressedTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int imageSize, Buffer data) {
		gl.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset,
				width, height, depth, format, imageSize, data);
	}

	public static void glUniformMatrix2x4dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix2x4dv(location, count, transpose, value, value_offset);
	}

	public static void glCompressedTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int imageSize, long data_buffer_offset) {
		gl.glCompressedTexSubImage3D(target, level, xoffset, yoffset, zoffset,
				width, height, depth, format, imageSize, data_buffer_offset);
	}

	public static void glUniformMatrix3dv(int location, int count, boolean transpose,
			DoubleBuffer value) {
		gl.glUniformMatrix3dv(location, count, transpose, value);
	}

	public static void glUniformMatrix3dv(int location, int count, boolean transpose,
			double[] value, int value_offset) {
		gl.glUniformMatrix3dv(location, count, transpose, value, value_offset);
	}

	public static void glCopyImageSubData(int srcName, int srcTarget, int srcLevel,
			int srcX, int srcY, int srcZ, int dstName, int dstTarget,
			int dstLevel, int dstX, int dstY, int dstZ, int srcWidth,
			int srcHeight, int srcDepth) {
		gl.glCopyImageSubData(srcName, srcTarget, srcLevel, srcX, srcY, srcZ,
				dstName, dstTarget, dstLevel, dstX, dstY, dstZ, srcWidth,
				srcHeight, srcDepth);
	}

	public static void glUniformMatrix3x2dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix3x2dv(location, count, transpose, value);
	}

	public static void glUniformMatrix3x2dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix3x2dv(location, count, transpose, value, value_offset);
	}

	public static void glCopyTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int x, int y, int width, int height) {
		gl.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y,
				width, height);
	}

	public static void glUniformMatrix3x4dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix3x4dv(location, count, transpose, value);
	}

	public static void glUniformMatrix3x4dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix3x4dv(location, count, transpose, value, value_offset);
	}

	public static int glCreateProgram() {
		return gl.glCreateProgram();
	}

	public static int glCreateShader(int type) {
		return gl.glCreateShader(type);
	}

	public static void glUniformMatrix4dv(int location, int count, boolean transpose,
			DoubleBuffer value) {
		gl.glUniformMatrix4dv(location, count, transpose, value);
	}

	public static int glCreateShaderProgramv(int type, int count, String[] strings) {
		return gl.glCreateShaderProgramv(type, count, strings);
	}

	public static void glUniformMatrix4dv(int location, int count, boolean transpose,
			double[] value, int value_offset) {
		gl.glUniformMatrix4dv(location, count, transpose, value, value_offset);
	}

	public static void glDebugMessageControl(int source, int type, int severity,
			int count, IntBuffer ids, boolean enabled) {
		gl.glDebugMessageControl(source, type, severity, count, ids, enabled);
	}

	public static void glUniformMatrix4x2dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix4x2dv(location, count, transpose, value);
	}

	public static void glDebugMessageControl(int source, int type, int severity,
			int count, int[] ids, int ids_offset, boolean enabled) {
		gl.glDebugMessageControl(source, type, severity, count, ids,
				ids_offset, enabled);
	}

	public static void glUniformMatrix4x2dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix4x2dv(location, count, transpose, value, value_offset);
	}

	public static void glUniformMatrix4x3dv(int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glUniformMatrix4x3dv(location, count, transpose, value);
	}

	public static void glDebugMessageInsert(int source, int type, int id,
			int severity, int length, String buf) {
		gl.glDebugMessageInsert(source, type, id, severity, length, buf);
	}

	public static void glUniformMatrix4x3dv(int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glUniformMatrix4x3dv(location, count, transpose, value, value_offset);
	}

	public static void glDeleteProgram(int program) {
		gl.glDeleteProgram(program);
	}

	public static void glUniformSubroutinesuiv(int shadertype, int count,
			IntBuffer indices) {
		gl.glUniformSubroutinesuiv(shadertype, count, indices);
	}

	public static void glDeleteProgramPipelines(int n, IntBuffer pipelines) {
		gl.glDeleteProgramPipelines(n, pipelines);
	}

	public static void glUniformSubroutinesuiv(int shadertype, int count,
			int[] indices, int indices_offset) {
		gl.glUniformSubroutinesuiv(shadertype, count, indices, indices_offset);
	}

	public static void glDeleteProgramPipelines(int n, int[] pipelines,
			int pipelines_offset) {
		gl.glDeleteProgramPipelines(n, pipelines, pipelines_offset);
	}

	public static void glVertexAttribP1ui(int index, int type, boolean normalized,
			int value) {
		gl.glVertexAttribP1ui(index, type, normalized, value);
	}

	public static void glVertexAttribP1uiv(int index, int type, boolean normalized,
			IntBuffer value) {
		gl.glVertexAttribP1uiv(index, type, normalized, value);
	}

	public static void glDeleteQueries(int n, IntBuffer ids) {
		gl.glDeleteQueries(n, ids);
	}

	public static void glVertexAttribP1uiv(int index, int type, boolean normalized,
			int[] value, int value_offset) {
		gl.glVertexAttribP1uiv(index, type, normalized, value, value_offset);
	}

	public static void glDeleteQueries(int n, int[] ids, int ids_offset) {
		gl.glDeleteQueries(n, ids, ids_offset);
	}

	public static void glVertexAttribP2ui(int index, int type, boolean normalized,
			int value) {
		gl.glVertexAttribP2ui(index, type, normalized, value);
	}

	public static void glDeleteShader(int shader) {
		gl.glDeleteShader(shader);
	}

	public static void glDetachShader(int program, int shader) {
		gl.glDetachShader(program, shader);
	}

	public static void glVertexAttribP2uiv(int index, int type, boolean normalized,
			IntBuffer value) {
		gl.glVertexAttribP2uiv(index, type, normalized, value);
	}

	public static void glDisableVertexAttribArray(int index) {
		gl.glDisableVertexAttribArray(index);
	}

	public static void glVertexAttribP2uiv(int index, int type, boolean normalized,
			int[] value, int value_offset) {
		gl.glVertexAttribP2uiv(index, type, normalized, value, value_offset);
	}

	public static void glDrawArraysInstancedBaseInstance(int mode, int first,
			int count, int instancecount, int baseinstance) {
		gl.glDrawArraysInstancedBaseInstance(mode, first, count, instancecount,
				baseinstance);
	}

	public static void glVertexAttribP3ui(int index, int type, boolean normalized,
			int value) {
		gl.glVertexAttribP3ui(index, type, normalized, value);
	}

	public static void glDrawBuffers(int n, IntBuffer bufs) {
		gl.glDrawBuffers(n, bufs);
	}

	public static void glVertexAttribP3uiv(int index, int type, boolean normalized,
			IntBuffer value) {
		gl.glVertexAttribP3uiv(index, type, normalized, value);
	}

	public static void glVertexAttribP3uiv(int index, int type, boolean normalized,
			int[] value, int value_offset) {
		gl.glVertexAttribP3uiv(index, type, normalized, value, value_offset);
	}

	public static void glDrawBuffers(int n, int[] bufs, int bufs_offset) {
		gl.glDrawBuffers(n, bufs, bufs_offset);
	}

	public static void glVertexAttribP4ui(int index, int type, boolean normalized,
			int value) {
		gl.glVertexAttribP4ui(index, type, normalized, value);
	}

	public static void glDrawElementsInstancedBaseInstance(int mode, int count,
			int type, long indices_buffer_offset, int instancecount,
			int baseinstance) {
		gl.glDrawElementsInstancedBaseInstance(mode, count, type,
				indices_buffer_offset, instancecount, baseinstance);
	}

	public static void glVertexAttribP4uiv(int index, int type, boolean normalized,
			IntBuffer value) {
		gl.glVertexAttribP4uiv(index, type, normalized, value);
	}

	public static void glBeginQueryIndexed(int target, int index, int id) {
		gl.glBeginQueryIndexed(target, index, id);
	}

	public static void glVertexAttribP4uiv(int index, int type, boolean normalized,
			int[] value, int value_offset) {
		gl.glVertexAttribP4uiv(index, type, normalized, value, value_offset);
	}

	public static void glDrawElementsInstancedBaseVertexBaseInstance(int mode,
			int count, int type, long indices_buffer_offset, int instancecount,
			int basevertex, int baseinstance) {
		gl.glDrawElementsInstancedBaseVertexBaseInstance(mode, count, type,
				indices_buffer_offset, instancecount, basevertex, baseinstance);
	}

	public static void glBindFragDataLocation(int program, int color, String name) {
		gl.glBindFragDataLocation(program, color, name);
	}

	public static void glViewportArrayv(int first, int count, FloatBuffer v) {
		gl.glViewportArrayv(first, count, v);
	}

	public static void glBlendEquationSeparatei(int buf, int modeRGB, int modeAlpha) {
		gl.glBlendEquationSeparatei(buf, modeRGB, modeAlpha);
	}

	public static void glEnableVertexAttribArray(int index) {
		gl.glEnableVertexAttribArray(index);
	}

	public static void glViewportArrayv(int first, int count, float[] v, int v_offset) {
		gl.glViewportArrayv(first, count, v, v_offset);
	}

	public static void glBlendEquationi(int buf, int mode) {
		gl.glBlendEquationi(buf, mode);
	}

	public static void glEndQuery(int target) {
		gl.glEndQuery(target);
	}

	public static void glViewportIndexedf(int index, float x, float y, float w, float h) {
		gl.glViewportIndexedf(index, x, y, w, h);
	}

	public static void glFramebufferTexture3D(int target, int attachment,
			int textarget, int texture, int level, int zoffset) {
		gl.glFramebufferTexture3D(target, attachment, textarget, texture,
				level, zoffset);
	}

	public static void glBlendFuncSeparatei(int buf, int srcRGB, int dstRGB,
			int srcAlpha, int dstAlpha) {
		gl.glBlendFuncSeparatei(buf, srcRGB, dstRGB, srcAlpha, dstAlpha);
	}

	public static void glViewportIndexedfv(int index, FloatBuffer v) {
		gl.glViewportIndexedfv(index, v);
	}

	public static void glGenProgramPipelines(int n, IntBuffer pipelines) {
		gl.glGenProgramPipelines(n, pipelines);
	}

	public static void glBlendFunci(int buf, int src, int dst) {
		gl.glBlendFunci(buf, src, dst);
	}

	public static void glViewportIndexedfv(int index, float[] v, int v_offset) {
		gl.glViewportIndexedfv(index, v, v_offset);
	}

	public static void glGenProgramPipelines(int n, int[] pipelines,
			int pipelines_offset) {
		gl.glGenProgramPipelines(n, pipelines, pipelines_offset);
	}

	public static void glBufferAddressRangeNV(int pname, int index, long address,
			long length) {
		gl.glBufferAddressRangeNV(pname, index, address, length);
	}

	public static void glBufferPageCommitmentARB(int target, long offset, long size,
			boolean commit) {
		gl.glBufferPageCommitmentARB(target, offset, size, commit);
	}

	public static void glGenQueries(int n, IntBuffer ids) {
		gl.glGenQueries(n, ids);
	}

	public static void glClampColor(int target, int clamp) {
		gl.glClampColor(target, clamp);
	}

	public static void glGenQueries(int n, int[] ids, int ids_offset) {
		gl.glGenQueries(n, ids, ids_offset);
	}

	public static void glClearBufferData(int target, int internalformat, int format,
			int type, Buffer data) {
		gl.glClearBufferData(target, internalformat, format, type, data);
	}

	public static void glBeginConditionalRender(int id, int mode) {
		gl.glBeginConditionalRender(id, mode);
	}

	public static void glBeginTransformFeedback(int primitiveMode) {
		gl.glBeginTransformFeedback(primitiveMode);
	}

	public static void glGetActiveAttrib(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		gl.glGetActiveAttrib(program, index, bufSize, length, size, type, name);
	}

	public static void glClearBufferSubData(int target, int internalformat,
			long offset, long size, int format, int type, Buffer data) {
		gl.glClearBufferSubData(target, internalformat, offset, size, format,
				type, data);
	}

	public static void glBindBufferBase(int target, int index, int buffer) {
		gl.glBindBufferBase(target, index, buffer);
	}

	public static void glColorFormatNV(int size, int type, int stride) {
		gl.glColorFormatNV(size, type, stride);
	}

	public static void glGetActiveAttrib(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		gl.glGetActiveAttrib(program, index, bufSize, length, length_offset,
				size, size_offset, type, type_offset, name, name_offset);
	}

	public static void glColorMaski(int index, boolean r, boolean g, boolean b,
			boolean a) {
		gl.glColorMaski(index, r, g, b, a);
	}

	public static void glBindBufferRange(int target, int index, int buffer,
			long offset, long size) {
		gl.glBindBufferRange(target, index, buffer, offset, size);
	}

	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			Buffer data) {
		gl.glCompressedTexImage1D(target, level, internalformat, width, border,
				imageSize, data);
	}

	public static void glGetActiveUniform(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		gl.glGetActiveUniform(program, index, bufSize, length, size, type, name);
	}

	public static void glBindImageTexture(int unit, int texture, int level,
			boolean layered, int layer, int access, int format) {
		gl.glBindImageTexture(unit, texture, level, layered, layer, access,
				format);
	}

	public static void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			long data_buffer_offset) {
		gl.glCompressedTexImage1D(target, level, internalformat, width, border,
				imageSize, data_buffer_offset);
	}

	public static void glBindTransformFeedback(int target, int id) {
		gl.glBindTransformFeedback(target, id);
	}

	public static void glGetActiveUniform(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		gl.glGetActiveUniform(program, index, bufSize, length, length_offset,
				size, size_offset, type, type_offset, name, name_offset);
	}

	public static void glBindVertexArray(int array) {
		gl.glBindVertexArray(array);
	}

	public static void glCompressedTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int imageSize, Buffer data) {
		gl.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data);
	}

	public static void glGetAttachedShaders(int program, int maxCount,
			IntBuffer count, IntBuffer shaders) {
		gl.glGetAttachedShaders(program, maxCount, count, shaders);
	}

	public static void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1,
			int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
		gl.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1,
				dstY1, mask, filter);
	}

	public static void glCompressedTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int imageSize, long data_buffer_offset) {
		gl.glCompressedTexSubImage1D(target, level, xoffset, width, format,
				imageSize, data_buffer_offset);
	}

	public static void glGetAttachedShaders(int program, int maxCount, int[] count,
			int count_offset, int[] shaders, int shaders_offset) {
		gl.glGetAttachedShaders(program, maxCount, count, count_offset,
				shaders, shaders_offset);
	}

	public static void glCopyTexImage1D(int target, int level, int internalformat,
			int x, int y, int width, int border) {
		gl.glCopyTexImage1D(target, level, internalformat, x, y, width, border);
	}

	public static void glClearBufferfi(int buffer, int drawbuffer, float depth,
			int stencil) {
		gl.glClearBufferfi(buffer, drawbuffer, depth, stencil);
	}

	public static int glGetAttribLocation(int program, String name) {
		return gl.glGetAttribLocation(program, name);
	}

	public static void glClearBufferfv(int buffer, int drawbuffer, FloatBuffer value) {
		gl.glClearBufferfv(buffer, drawbuffer, value);
	}

	public static void glCopyTexSubImage1D(int target, int level, int xoffset, int x,
			int y, int width) {
		gl.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
	}

	public static int glGetDebugMessageLog(int count, int bufSize, IntBuffer sources,
			IntBuffer types, IntBuffer ids, IntBuffer severities,
			IntBuffer lengths, ByteBuffer messageLog) {
		return gl.glGetDebugMessageLog(count, bufSize, sources, types, ids,
				severities, lengths, messageLog);
	}

	public static void glClearBufferfv(int buffer, int drawbuffer, float[] value,
			int value_offset) {
		gl.glClearBufferfv(buffer, drawbuffer, value, value_offset);
	}

	public static void glDebugMessageEnableAMD(int category, int severity, int count,
			IntBuffer ids, boolean enabled) {
		gl.glDebugMessageEnableAMD(category, severity, count, ids, enabled);
	}

	public static void glClearBufferiv(int buffer, int drawbuffer, IntBuffer value) {
		gl.glClearBufferiv(buffer, drawbuffer, value);
	}

	public static void glDebugMessageEnableAMD(int category, int severity, int count,
			int[] ids, int ids_offset, boolean enabled) {
		gl.glDebugMessageEnableAMD(category, severity, count, ids, ids_offset,
				enabled);
	}

	public static void glActiveTexture(int texture) {
		gl.glActiveTexture(texture);
	}

	public static void glClearBufferiv(int buffer, int drawbuffer, int[] value,
			int value_offset) {
		gl.glClearBufferiv(buffer, drawbuffer, value, value_offset);
	}

	public static void glDebugMessageInsertAMD(int category, int severity, int id,
			int length, String buf) {
		gl.glDebugMessageInsertAMD(category, severity, id, length, buf);
	}

	public static void glBindBuffer(int target, int buffer) {
		gl.glBindBuffer(target, buffer);
	}

	public static int glGetDebugMessageLog(int count, int bufSize, int[] sources,
			int sources_offset, int[] types, int types_offset, int[] ids,
			int ids_offset, int[] severities, int severities_offset,
			int[] lengths, int lengths_offset, byte[] messageLog,
			int messageLog_offset) {
		return gl.glGetDebugMessageLog(count, bufSize, sources, sources_offset,
				types, types_offset, ids, ids_offset, severities,
				severities_offset, lengths, lengths_offset, messageLog,
				messageLog_offset);
	}

	public static void glClearBufferuiv(int buffer, int drawbuffer, IntBuffer value) {
		gl.glClearBufferuiv(buffer, drawbuffer, value);
	}

	public static void glDisableClientState(int cap) {
		gl.glDisableClientState(cap);
	}

	public static void glBindFramebuffer(int target, int framebuffer) {
		gl.glBindFramebuffer(target, framebuffer);
	}

	public static void glClearBufferuiv(int buffer, int drawbuffer, int[] value,
			int value_offset) {
		gl.glClearBufferuiv(buffer, drawbuffer, value, value_offset);
	}

	public static void glDisablei(int target, int index) {
		gl.glDisablei(target, index);
	}

	public static void glBindRenderbuffer(int target, int renderbuffer) {
		gl.glBindRenderbuffer(target, renderbuffer);
	}

	public static void glCopyBufferSubData(int readTarget, int writeTarget,
			long readOffset, long writeOffset, long size) {
		gl.glCopyBufferSubData(readTarget, writeTarget, readOffset,
				writeOffset, size);
	}

	public static void glGetMultisamplefv(int pname, int index, FloatBuffer val) {
		gl.glGetMultisamplefv(pname, index, val);
	}

	public static void glDrawBuffer(int mode) {
		gl.glDrawBuffer(mode);
	}

	public static void glDrawTransformFeedback(int mode, int id) {
		gl.glDrawTransformFeedback(mode, id);
	}

	public static void glBindTexture(int target, int texture) {
		gl.glBindTexture(target, texture);
	}

	public static void glDeleteTransformFeedbacks(int n, IntBuffer ids) {
		gl.glDeleteTransformFeedbacks(n, ids);
	}

	public static void glGetMultisamplefv(int pname, int index, float[] val,
			int val_offset) {
		gl.glGetMultisamplefv(pname, index, val, val_offset);
	}

	public static void glDrawTransformFeedbackStream(int mode, int id, int stream) {
		gl.glDrawTransformFeedbackStream(mode, id, stream);
	}

	public static void glBlendEquation(int mode) {
		gl.glBlendEquation(mode);
	}

	public static void glGetObjectLabel(int identifier, int name, int bufSize,
			IntBuffer length, ByteBuffer label) {
		gl.glGetObjectLabel(identifier, name, bufSize, length, label);
	}

	public static void glDeleteTransformFeedbacks(int n, int[] ids, int ids_offset) {
		gl.glDeleteTransformFeedbacks(n, ids, ids_offset);
	}

	public static void glEdgeFlagFormatNV(int stride) {
		gl.glEdgeFlagFormatNV(stride);
	}

	public static void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
		gl.glBlendEquationSeparate(modeRGB, modeAlpha);
	}

	public static void glEnableClientState(int cap) {
		gl.glEnableClientState(cap);
	}

	public static void glDeleteVertexArrays(int n, IntBuffer arrays) {
		gl.glDeleteVertexArrays(n, arrays);
	}

	public static void glEnablei(int target, int index) {
		gl.glEnablei(target, index);
	}

	public static void glGetObjectLabel(int identifier, int name, int bufSize,
			int[] length, int length_offset, byte[] label, int label_offset) {
		gl.glGetObjectLabel(identifier, name, bufSize, length, length_offset,
				label, label_offset);
	}

	public static void glBlendFunc(int sfactor, int dfactor) {
		gl.glBlendFunc(sfactor, dfactor);
	}

	public static void glDeleteVertexArrays(int n, int[] arrays, int arrays_offset) {
		gl.glDeleteVertexArrays(n, arrays, arrays_offset);
	}

	public static void glEndQueryIndexed(int target, int index) {
		gl.glEndQueryIndexed(target, index);
	}

	public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		gl.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public static void glGetObjectPtrLabel(Buffer ptr, int bufSize, IntBuffer length,
			ByteBuffer label) {
		gl.glGetObjectPtrLabel(ptr, bufSize, length, label);
	}

	public static void glFogCoordFormatNV(int type, int stride) {
		gl.glFogCoordFormatNV(type, stride);
	}

	public static void glDrawArraysInstanced(int mode, int first, int count,
			int instancecount) {
		gl.glDrawArraysInstanced(mode, first, count, instancecount);
	}

	public static void glFramebufferTexture1D(int target, int attachment,
			int textarget, int texture, int level) {
		gl.glFramebufferTexture1D(target, attachment, textarget, texture, level);
	}

	public static void glBufferData(int target, long size, Buffer data, int usage) {
		gl.glBufferData(target, size, data, usage);
	}

	public static void glGetObjectPtrLabel(Buffer ptr, int bufSize, int[] length,
			int length_offset, byte[] label, int label_offset) {
		gl.glGetObjectPtrLabel(ptr, bufSize, length, length_offset, label,
				label_offset);
	}

	public static void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex,
			int pname, IntBuffer params) {
		gl.glGetActiveAtomicCounterBufferiv(program, bufferIndex, pname, params);
	}

	public static void glDrawElementsInstanced(int mode, int count, int type,
			long indices_buffer_offset, int instancecount) {
		gl.glDrawElementsInstanced(mode, count, type, indices_buffer_offset,
				instancecount);
	}

	public static void glGetProgramBinary(int program, int bufSize, IntBuffer length,
			IntBuffer binaryFormat, Buffer binary) {
		gl.glGetProgramBinary(program, bufSize, length, binaryFormat, binary);
	}

	public static void glBufferSubData(int target, long offset, long size, Buffer data) {
		gl.glBufferSubData(target, offset, size, data);
	}

	public static void glGetActiveAtomicCounterBufferiv(int program, int bufferIndex,
			int pname, int[] params, int params_offset) {
		gl.glGetActiveAtomicCounterBufferiv(program, bufferIndex, pname,
				params, params_offset);
	}

	public static void glGetActiveUniformName(int program, int uniformIndex,
			int bufSize, IntBuffer length, ByteBuffer uniformName) {
		gl.glGetActiveUniformName(program, uniformIndex, bufSize, length,
				uniformName);
	}

	public static void glDrawRangeElements(int mode, int start, int end, int count,
			int type, long indices_buffer_offset) {
		gl.glDrawRangeElements(mode, start, end, count, type,
				indices_buffer_offset);
	}

	public static int glCheckFramebufferStatus(int target) {
		return gl.glCheckFramebufferStatus(target);
	}

	public static void glGetProgramBinary(int program, int bufSize, int[] length,
			int length_offset, int[] binaryFormat, int binaryFormat_offset,
			Buffer binary) {
		gl.glGetProgramBinary(program, bufSize, length, length_offset,
				binaryFormat, binaryFormat_offset, binary);
	}

	public static void glEndConditionalRender() {
		gl.glEndConditionalRender();
	}

	public static void glClear(int mask) {
		gl.glClear(mask);
	}

	public static void glGetActiveUniformName(int program, int uniformIndex,
			int bufSize, int[] length, int length_offset, byte[] uniformName,
			int uniformName_offset) {
		gl.glGetActiveUniformName(program, uniformIndex, bufSize, length,
				length_offset, uniformName, uniformName_offset);
	}

	public static void glClearColor(float red, float green, float blue, float alpha) {
		gl.glClearColor(red, green, blue, alpha);
	}

	public static void glEndTransformFeedback() {
		gl.glEndTransformFeedback();
	}

	public static void glGetProgramInfoLog(int program, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		gl.glGetProgramInfoLog(program, bufSize, length, infoLog);
	}

	public static void glGetBufferParameterui64vNV(int target, int pname,
			LongBuffer params) {
		gl.glGetBufferParameterui64vNV(target, pname, params);
	}

	public static void glFramebufferParameteri(int target, int pname, int param) {
		gl.glFramebufferParameteri(target, pname, param);
	}

	public static void glGetProgramInfoLog(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		gl.glGetProgramInfoLog(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public static void glGetBufferParameterui64vNV(int target, int pname,
			long[] params, int params_offset) {
		gl.glGetBufferParameterui64vNV(target, pname, params, params_offset);
	}

	public static void glClearStencil(int s) {
		gl.glClearStencil(s);
	}

	public static void glFramebufferTextureEXT(int target, int attachment,
			int texture, int level) {
		gl.glFramebufferTextureEXT(target, attachment, texture, level);
	}

	public static void glGetProgramPipelineInfoLog(int pipeline, int bufSize,
			IntBuffer length, ByteBuffer infoLog) {
		gl.glGetProgramPipelineInfoLog(pipeline, bufSize, length, infoLog);
	}

	public static void glGetBufferSubData(int target, long offset, long size,
			Buffer data) {
		gl.glGetBufferSubData(target, offset, size, data);
	}

	public static void glColorMask(boolean red, boolean green, boolean blue,
			boolean alpha) {
		gl.glColorMask(red, green, blue, alpha);
	}

	public static void glFramebufferTextureLayer(int target, int attachment,
			int texture, int level, int layer) {
		gl.glFramebufferTextureLayer(target, attachment, texture, level, layer);
	}

	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, Buffer data) {
		gl.glCompressedTexImage2D(target, level, internalformat, width, height,
				border, imageSize, data);
	}

	public static void glGetCompressedTexImage(int target, int level, Buffer img) {
		gl.glGetCompressedTexImage(target, level, img);
	}

	public static void glGetProgramPipelineInfoLog(int pipeline, int bufSize,
			int[] length, int length_offset, byte[] infoLog, int infoLog_offset) {
		gl.glGetProgramPipelineInfoLog(pipeline, bufSize, length,
				length_offset, infoLog, infoLog_offset);
	}

	public static void glGenTransformFeedbacks(int n, IntBuffer ids) {
		gl.glGenTransformFeedbacks(n, ids);
	}

	public static void glGetCompressedTexImage(int target, int level,
			long img_buffer_offset) {
		gl.glGetCompressedTexImage(target, level, img_buffer_offset);
	}

	public static void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, long data_buffer_offset) {
		gl.glCompressedTexImage2D(target, level, internalformat, width, height,
				border, imageSize, data_buffer_offset);
	}

	public static void glGetProgramPipelineiv(int pipeline, int pname, IntBuffer params) {
		gl.glGetProgramPipelineiv(pipeline, pname, params);
	}

	public static int glGetDebugMessageLogAMD(int count, int bufsize,
			IntBuffer categories, IntBuffer severities, IntBuffer ids,
			IntBuffer lengths, ByteBuffer message) {
		return gl.glGetDebugMessageLogAMD(count, bufsize, categories,
				severities, ids, lengths, message);
	}

	public static void glGenTransformFeedbacks(int n, int[] ids, int ids_offset) {
		gl.glGenTransformFeedbacks(n, ids, ids_offset);
	}

	public static void glCompressedTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int imageSize,
			Buffer data) {
		gl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data);
	}

	public static void glGenVertexArrays(int n, IntBuffer arrays) {
		gl.glGenVertexArrays(n, arrays);
	}

	public static void glGetProgramPipelineiv(int pipeline, int pname, int[] params,
			int params_offset) {
		gl.glGetProgramPipelineiv(pipeline, pname, params, params_offset);
	}

	public static int glGetDebugMessageLogAMD(int count, int bufsize,
			int[] categories, int categories_offset, int[] severities,
			int severities_offset, int[] ids, int ids_offset, int[] lengths,
			int lengths_offset, byte[] message, int message_offset) {
		return gl.glGetDebugMessageLogAMD(count, bufsize, categories,
				categories_offset, severities, severities_offset, ids,
				ids_offset, lengths, lengths_offset, message, message_offset);
	}

	public static void glGetProgramiv(int program, int pname, IntBuffer params) {
		gl.glGetProgramiv(program, pname, params);
	}

	public static void glGenVertexArrays(int n, int[] arrays, int arrays_offset) {
		gl.glGenVertexArrays(n, arrays, arrays_offset);
	}

	public static void glCompressedTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int imageSize,
			long data_buffer_offset) {
		gl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, imageSize, data_buffer_offset);
	}

	public static void glGetDoublev(int pname, DoubleBuffer params) {
		gl.glGetDoublev(pname, params);
	}

	public static void glGetActiveUniformBlockName(int program, int uniformBlockIndex,
			int bufSize, IntBuffer length, ByteBuffer uniformBlockName) {
		gl.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize,
				length, uniformBlockName);
	}

	public static void glGetProgramiv(int program, int pname, int[] params,
			int params_offset) {
		gl.glGetProgramiv(program, pname, params, params_offset);
	}

	public static void glGetDoublev(int pname, double[] params, int params_offset) {
		gl.glGetDoublev(pname, params, params_offset);
	}

	public static void glCopyTexImage2D(int target, int level, int internalformat,
			int x, int y, int width, int height, int border) {
		gl.glCopyTexImage2D(target, level, internalformat, x, y, width, height,
				border);
	}

	public static void glGetIntegerui64i_vNV(int value, int index, LongBuffer result) {
		gl.glGetIntegerui64i_vNV(value, index, result);
	}

	public static void glGetQueryObjecti64v(int id, int pname, LongBuffer params) {
		gl.glGetQueryObjecti64v(id, pname, params);
	}

	public static void glGetActiveUniformBlockName(int program, int uniformBlockIndex,
			int bufSize, int[] length, int length_offset,
			byte[] uniformBlockName, int uniformBlockName_offset) {
		gl.glGetActiveUniformBlockName(program, uniformBlockIndex, bufSize,
				length, length_offset, uniformBlockName,
				uniformBlockName_offset);
	}

	public static void glCopyTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int x, int y, int width, int height) {
		gl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width,
				height);
	}

	public static void glGetIntegerui64i_vNV(int value, int index, long[] result,
			int result_offset) {
		gl.glGetIntegerui64i_vNV(value, index, result, result_offset);
	}

	public static void glGetQueryObjecti64v(int id, int pname, long[] params,
			int params_offset) {
		gl.glGetQueryObjecti64v(id, pname, params, params_offset);
	}

	public static void glGetIntegerui64vNV(int value, LongBuffer result) {
		gl.glGetIntegerui64vNV(value, result);
	}

	public static void glGetActiveUniformBlockiv(int program, int uniformBlockIndex,
			int pname, IntBuffer params) {
		gl.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
	}

	public static void glCullFace(int mode) {
		gl.glCullFace(mode);
	}

	public static void glGetIntegerui64vNV(int value, long[] result, int result_offset) {
		gl.glGetIntegerui64vNV(value, result, result_offset);
	}

	public static void glGetQueryObjectiv(int id, int pname, IntBuffer params) {
		gl.glGetQueryObjectiv(id, pname, params);
	}

	public static void glDeleteBuffers(int n, IntBuffer buffers) {
		gl.glDeleteBuffers(n, buffers);
	}

	public static void glGetActiveUniformBlockiv(int program, int uniformBlockIndex,
			int pname, int[] params, int params_offset) {
		gl.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params,
				params_offset);
	}

	public static void glGetInternalformati64v(int target, int internalformat,
			int pname, int bufSize, LongBuffer params) {
		gl.glGetInternalformati64v(target, internalformat, pname, bufSize,
				params);
	}

	public static void glGetQueryObjectiv(int id, int pname, int[] params,
			int params_offset) {
		gl.glGetQueryObjectiv(id, pname, params, params_offset);
	}

	public static void glDeleteBuffers(int n, int[] buffers, int buffers_offset) {
		gl.glDeleteBuffers(n, buffers, buffers_offset);
	}

	public static void glGetActiveUniformsiv(int program, int uniformCount,
			IntBuffer uniformIndices, int pname, IntBuffer params) {
		gl.glGetActiveUniformsiv(program, uniformCount, uniformIndices, pname,
				params);
	}

	public static void glGetInternalformati64v(int target, int internalformat,
			int pname, int bufSize, long[] params, int params_offset) {
		gl.glGetInternalformati64v(target, internalformat, pname, bufSize,
				params, params_offset);
	}

	public static void glGetQueryObjectui64v(int id, int pname, LongBuffer params) {
		gl.glGetQueryObjectui64v(id, pname, params);
	}

	public static void glDeleteFramebuffers(int n, IntBuffer framebuffers) {
		gl.glDeleteFramebuffers(n, framebuffers);
	}

	public static void glGetNamedBufferParameterui64vNV(int buffer, int pname,
			LongBuffer params) {
		gl.glGetNamedBufferParameterui64vNV(buffer, pname, params);
	}

	public static void glGetActiveUniformsiv(int program, int uniformCount,
			int[] uniformIndices, int uniformIndices_offset, int pname,
			int[] params, int params_offset) {
		gl.glGetActiveUniformsiv(program, uniformCount, uniformIndices,
				uniformIndices_offset, pname, params, params_offset);
	}

	public static void glGetQueryObjectui64v(int id, int pname, long[] params,
			int params_offset) {
		gl.glGetQueryObjectui64v(id, pname, params, params_offset);
	}

	public static void glDeleteFramebuffers(int n, int[] framebuffers,
			int framebuffers_offset) {
		gl.glDeleteFramebuffers(n, framebuffers, framebuffers_offset);
	}

	public static void glGetNamedBufferParameterui64vNV(int buffer, int pname,
			long[] params, int params_offset) {
		gl.glGetNamedBufferParameterui64vNV(buffer, pname, params,
				params_offset);
	}

	public static void glGetBooleani_v(int target, int index, ByteBuffer data) {
		gl.glGetBooleani_v(target, index, data);
	}

	public static void glGetQueryIndexediv(int target, int index, int pname,
			IntBuffer params) {
		gl.glGetQueryIndexediv(target, index, pname, params);
	}

	public static void glGetQueryObjectuiv(int id, int pname, IntBuffer params) {
		gl.glGetQueryObjectuiv(id, pname, params);
	}

	public static void glDeleteRenderbuffers(int n, IntBuffer renderbuffers) {
		gl.glDeleteRenderbuffers(n, renderbuffers);
	}

	public static void glGetBooleani_v(int target, int index, byte[] data,
			int data_offset) {
		gl.glGetBooleani_v(target, index, data, data_offset);
	}

	public static void glGetQueryIndexediv(int target, int index, int pname,
			int[] params, int params_offset) {
		gl.glGetQueryIndexediv(target, index, pname, params, params_offset);
	}

	public static int glGetFragDataLocation(int program, String name) {
		return gl.glGetFragDataLocation(program, name);
	}

	public static void glGetQueryObjectuiv(int id, int pname, int[] params,
			int params_offset) {
		gl.glGetQueryObjectuiv(id, pname, params, params_offset);
	}

	public static void glDeleteRenderbuffers(int n, int[] renderbuffers,
			int renderbuffers_offset) {
		gl.glDeleteRenderbuffers(n, renderbuffers, renderbuffers_offset);
	}

	public static void glGetTexImage(int target, int level, int format, int type,
			Buffer pixels) {
		gl.glGetTexImage(target, level, format, type, pixels);
	}

	public static void glGetFramebufferParameteriv(int target, int pname,
			IntBuffer params) {
		gl.glGetFramebufferParameteriv(target, pname, params);
	}

	public static void glGetQueryiv(int target, int pname, IntBuffer params) {
		gl.glGetQueryiv(target, pname, params);
	}

	public static void glGetTexImage(int target, int level, int format, int type,
			long pixels_buffer_offset) {
		gl.glGetTexImage(target, level, format, type, pixels_buffer_offset);
	}

	public static void glDeleteTextures(int n, IntBuffer textures) {
		gl.glDeleteTextures(n, textures);
	}

	public static void glGetFramebufferParameteriv(int target, int pname,
			int[] params, int params_offset) {
		gl.glGetFramebufferParameteriv(target, pname, params, params_offset);
	}

	public static void glGetUniformui64vNV(int program, int location, LongBuffer params) {
		gl.glGetUniformui64vNV(program, location, params);
	}

	public static void glGetQueryiv(int target, int pname, int[] params,
			int params_offset) {
		gl.glGetQueryiv(target, pname, params, params_offset);
	}

	public static void glDeleteTextures(int n, int[] textures, int textures_offset) {
		gl.glDeleteTextures(n, textures, textures_offset);
	}

	public static void glGetUniformui64vNV(int program, int location, long[] params,
			int params_offset) {
		gl.glGetUniformui64vNV(program, location, params, params_offset);
	}

	public static void glGetIntegeri_v(int target, int index, IntBuffer data) {
		gl.glGetIntegeri_v(target, index, data);
	}

	public static void glGetSamplerParameterIiv(int sampler, int pname,
			IntBuffer params) {
		gl.glGetSamplerParameterIiv(sampler, pname, params);
	}

	public static void glDepthFunc(int func) {
		gl.glDepthFunc(func);
	}

	public static void glGetVertexAttribLdv(int index, int pname, DoubleBuffer params) {
		gl.glGetVertexAttribLdv(index, pname, params);
	}

	public static void glGetIntegeri_v(int target, int index, int[] data,
			int data_offset) {
		gl.glGetIntegeri_v(target, index, data, data_offset);
	}

	public static void glDepthMask(boolean flag) {
		gl.glDepthMask(flag);
	}

	public static void glGetSamplerParameterIiv(int sampler, int pname, int[] params,
			int params_offset) {
		gl.glGetSamplerParameterIiv(sampler, pname, params, params_offset);
	}

	public static void glGetVertexAttribLdv(int index, int pname, double[] params,
			int params_offset) {
		gl.glGetVertexAttribLdv(index, pname, params, params_offset);
	}

	public static void glGetInternalformativ(int target, int internalformat,
			int pname, int bufSize, IntBuffer params) {
		gl.glGetInternalformativ(target, internalformat, pname, bufSize, params);
	}

	public static void glDisable(int cap) {
		gl.glDisable(cap);
	}

	public static void glGetSamplerParameterIuiv(int sampler, int pname,
			IntBuffer params) {
		gl.glGetSamplerParameterIuiv(sampler, pname, params);
	}

	public static void glGetVertexAttribdv(int index, int pname, DoubleBuffer params) {
		gl.glGetVertexAttribdv(index, pname, params);
	}

	public static void glGetInternalformativ(int target, int internalformat,
			int pname, int bufSize, int[] params, int params_offset) {
		gl.glGetInternalformativ(target, internalformat, pname, bufSize,
				params, params_offset);
	}

	public static void glDrawArrays(int mode, int first, int count) {
		gl.glDrawArrays(mode, first, count);
	}

	public static void glGetVertexAttribdv(int index, int pname, double[] params,
			int params_offset) {
		gl.glGetVertexAttribdv(index, pname, params, params_offset);
	}

	public static void glGetSamplerParameterIuiv(int sampler, int pname, int[] params,
			int params_offset) {
		gl.glGetSamplerParameterIuiv(sampler, pname, params, params_offset);
	}

	public static String glGetStringi(int name, int index) {
		return gl.glGetStringi(name, index);
	}

	public static void glDrawElements(int mode, int count, int type,
			long indices_buffer_offset) {
		gl.glDrawElements(mode, count, type, indices_buffer_offset);
	}

	public static void glGetTexLevelParameterfv(int target, int level, int pname,
			FloatBuffer params) {
		gl.glGetTexLevelParameterfv(target, level, pname, params);
	}

	public static void glEnable(int cap) {
		gl.glEnable(cap);
	}

	public static void glGetnCompressedTexImage(int target, int lod, int bufSize,
			Buffer pixels) {
		gl.glGetnCompressedTexImage(target, lod, bufSize, pixels);
	}

	public static void glGetShaderInfoLog(int shader, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		gl.glGetShaderInfoLog(shader, bufSize, length, infoLog);
	}

	public static void glFinish() {
		gl.glFinish();
	}

	public static void glGetTexLevelParameterfv(int target, int level, int pname,
			float[] params, int params_offset) {
		gl.glGetTexLevelParameterfv(target, level, pname, params, params_offset);
	}

	public static void glGetnTexImage(int target, int level, int format, int type,
			int bufSize, Buffer pixels) {
		gl.glGetnTexImage(target, level, format, type, bufSize, pixels);
	}

	public static void glFlush() {
		gl.glFlush();
	}

	public static void glGetShaderInfoLog(int shader, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		gl.glGetShaderInfoLog(shader, bufSize, length, length_offset, infoLog,
				infoLog_offset);
	}

	public static void glGetTexLevelParameteriv(int target, int level, int pname,
			IntBuffer params) {
		gl.glGetTexLevelParameteriv(target, level, pname, params);
	}

	public static void glFlushMappedBufferRange(int target, long offset, long length) {
		gl.glFlushMappedBufferRange(target, offset, length);
	}

	public static void glGetnUniformdv(int program, int location, int bufSize,
			DoubleBuffer params) {
		gl.glGetnUniformdv(program, location, bufSize, params);
	}

	public static void glGetShaderSource(int shader, int bufSize, IntBuffer length,
			ByteBuffer source) {
		gl.glGetShaderSource(shader, bufSize, length, source);
	}

	public static void glGetTexLevelParameteriv(int target, int level, int pname,
			int[] params, int params_offset) {
		gl.glGetTexLevelParameteriv(target, level, pname, params, params_offset);
	}

	public static void glFramebufferRenderbuffer(int target, int attachment,
			int renderbuffertarget, int renderbuffer) {
		gl.glFramebufferRenderbuffer(target, attachment, renderbuffertarget,
				renderbuffer);
	}

	public static void glGetnUniformdv(int program, int location, int bufSize,
			double[] params, int params_offset) {
		gl.glGetnUniformdv(program, location, bufSize, params, params_offset);
	}

	public static void glGetTransformFeedbackVarying(int program, int index,
			int bufSize, IntBuffer length, IntBuffer size, IntBuffer type,
			ByteBuffer name) {
		gl.glGetTransformFeedbackVarying(program, index, bufSize, length, size,
				type, name);
	}

	public static void glGetShaderSource(int shader, int bufSize, int[] length,
			int length_offset, byte[] source, int source_offset) {
		gl.glGetShaderSource(shader, bufSize, length, length_offset, source,
				source_offset);
	}

	public static void glGetnUniformuiv(int program, int location, int bufSize,
			IntBuffer params) {
		gl.glGetnUniformuiv(program, location, bufSize, params);
	}

	public static void glFramebufferTexture2D(int target, int attachment,
			int textarget, int texture, int level) {
		gl.glFramebufferTexture2D(target, attachment, textarget, texture, level);
	}

	public static void glGetShaderiv(int shader, int pname, IntBuffer params) {
		gl.glGetShaderiv(shader, pname, params);
	}

	public static void glGetnUniformuiv(int program, int location, int bufSize,
			int[] params, int params_offset) {
		gl.glGetnUniformuiv(program, location, bufSize, params, params_offset);
	}

	public static void glGetTransformFeedbackVarying(int program, int index,
			int bufSize, int[] length, int length_offset, int[] size,
			int size_offset, int[] type, int type_offset, byte[] name,
			int name_offset) {
		gl.glGetTransformFeedbackVarying(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public static void glGetShaderiv(int shader, int pname, int[] params,
			int params_offset) {
		gl.glGetShaderiv(shader, pname, params, params_offset);
	}

	public static void glFrontFace(int mode) {
		gl.glFrontFace(mode);
	}

	public static void glGenBuffers(int n, IntBuffer buffers) {
		gl.glGenBuffers(n, buffers);
	}

	public static void glGetTexParameterIiv(int target, int pname, IntBuffer params) {
		gl.glGetTexParameterIiv(target, pname, params);
	}

	public static long glImportSyncEXT(int external_sync_type, long external_sync,
			int flags) {
		return gl.glImportSyncEXT(external_sync_type, external_sync, flags);
	}

	public static int glGetUniformBlockIndex(int program, String uniformBlockName) {
		return gl.glGetUniformBlockIndex(program, uniformBlockName);
	}

	public static void glIndexFormatNV(int type, int stride) {
		gl.glIndexFormatNV(type, stride);
	}

	public static void glGenBuffers(int n, int[] buffers, int buffers_offset) {
		gl.glGenBuffers(n, buffers, buffers_offset);
	}

	public static void glGetTexParameterIiv(int target, int pname, int[] params,
			int params_offset) {
		gl.glGetTexParameterIiv(target, pname, params, params_offset);
	}

	public static void glInvalidateBufferData(int buffer) {
		gl.glInvalidateBufferData(buffer);
	}

	public static void glGetUniformIndices(int program, int uniformCount,
			String[] uniformNames, IntBuffer uniformIndices) {
		gl.glGetUniformIndices(program, uniformCount, uniformNames,
				uniformIndices);
	}

	public static void glGenFramebuffers(int n, IntBuffer framebuffers) {
		gl.glGenFramebuffers(n, framebuffers);
	}

	public static void glInvalidateBufferSubData(int buffer, long offset, long length) {
		gl.glInvalidateBufferSubData(buffer, offset, length);
	}

	public static void glGetTexParameterIuiv(int target, int pname, IntBuffer params) {
		gl.glGetTexParameterIuiv(target, pname, params);
	}

	public static void glGetUniformIndices(int program, int uniformCount,
			String[] uniformNames, int[] uniformIndices,
			int uniformIndices_offset) {
		gl.glGetUniformIndices(program, uniformCount, uniformNames,
				uniformIndices, uniformIndices_offset);
	}

	public static void glInvalidateTexImage(int texture, int level) {
		gl.glInvalidateTexImage(texture, level);
	}

	public static void glGenFramebuffers(int n, int[] framebuffers,
			int framebuffers_offset) {
		gl.glGenFramebuffers(n, framebuffers, framebuffers_offset);
	}

	public static void glInvalidateTexSubImage(int texture, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth) {
		gl.glInvalidateTexSubImage(texture, level, xoffset, yoffset, zoffset,
				width, height, depth);
	}

	public static void glGetTexParameterIuiv(int target, int pname, int[] params,
			int params_offset) {
		gl.glGetTexParameterIuiv(target, pname, params, params_offset);
	}

	public static void glGetUniformuiv(int program, int location, IntBuffer params) {
		gl.glGetUniformuiv(program, location, params);
	}

	public static void glGenRenderbuffers(int n, IntBuffer renderbuffers) {
		gl.glGenRenderbuffers(n, renderbuffers);
	}

	public static boolean glIsBufferResidentNV(int target) {
		return gl.glIsBufferResidentNV(target);
	}

	public static int glGetUniformLocation(int program, String name) {
		return gl.glGetUniformLocation(program, name);
	}

	public static void glGetUniformuiv(int program, int location, int[] params,
			int params_offset) {
		gl.glGetUniformuiv(program, location, params, params_offset);
	}

	public static boolean glIsEnabledi(int target, int index) {
		return gl.glIsEnabledi(target, index);
	}

	public static void glGenRenderbuffers(int n, int[] renderbuffers,
			int renderbuffers_offset) {
		gl.glGenRenderbuffers(n, renderbuffers, renderbuffers_offset);
	}

	public static void glGetUniformfv(int program, int location, FloatBuffer params) {
		gl.glGetUniformfv(program, location, params);
	}

	public static void glGetVertexAttribIiv(int index, int pname, IntBuffer params) {
		gl.glGetVertexAttribIiv(index, pname, params);
	}

	public static boolean glIsNamedBufferResidentNV(int buffer) {
		return gl.glIsNamedBufferResidentNV(buffer);
	}

	public static void glLogicOp(int opcode) {
		gl.glLogicOp(opcode);
	}

	public static void glGetUniformfv(int program, int location, float[] params,
			int params_offset) {
		gl.glGetUniformfv(program, location, params, params_offset);
	}

	public static void glGenTextures(int n, IntBuffer textures) {
		gl.glGenTextures(n, textures);
	}

	public static void glGetVertexAttribIiv(int index, int pname, int[] params,
			int params_offset) {
		gl.glGetVertexAttribIiv(index, pname, params, params_offset);
	}

	public static void glMakeBufferNonResidentNV(int target) {
		gl.glMakeBufferNonResidentNV(target);
	}

	public static void glMakeBufferResidentNV(int target, int access) {
		gl.glMakeBufferResidentNV(target, access);
	}

	public static void glGetUniformiv(int program, int location, IntBuffer params) {
		gl.glGetUniformiv(program, location, params);
	}

	public static void glGetVertexAttribIuiv(int index, int pname, IntBuffer params) {
		gl.glGetVertexAttribIuiv(index, pname, params);
	}

	public static void glGenTextures(int n, int[] textures, int textures_offset) {
		gl.glGenTextures(n, textures, textures_offset);
	}

	public static void glMakeNamedBufferNonResidentNV(int buffer) {
		gl.glMakeNamedBufferNonResidentNV(buffer);
	}

	public static void glGetUniformiv(int program, int location, int[] params,
			int params_offset) {
		gl.glGetUniformiv(program, location, params, params_offset);
	}

	public static void glGenerateMipmap(int target) {
		gl.glGenerateMipmap(target);
	}

	public static void glMakeNamedBufferResidentNV(int buffer, int access) {
		gl.glMakeNamedBufferResidentNV(buffer, access);
	}

	public static void glGetVertexAttribIuiv(int index, int pname, int[] params,
			int params_offset) {
		gl.glGetVertexAttribIuiv(index, pname, params, params_offset);
	}

	public static void glMinSampleShading(float value) {
		gl.glMinSampleShading(value);
	}

	public static void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
		gl.glGetVertexAttribfv(index, pname, params);
	}

	public static void glGetBooleanv(int pname, ByteBuffer data) {
		gl.glGetBooleanv(pname, data);
	}

	public static void glInvalidateFramebuffer(int target, int numAttachments,
			IntBuffer attachments) {
		gl.glInvalidateFramebuffer(target, numAttachments, attachments);
	}

	public static void glMultiDrawArrays(int mode, IntBuffer first, IntBuffer count,
			int drawcount) {
		gl.glMultiDrawArrays(mode, first, count, drawcount);
	}

	public static void glGetBooleanv(int pname, byte[] data, int data_offset) {
		gl.glGetBooleanv(pname, data, data_offset);
	}

	public static void glGetVertexAttribfv(int index, int pname, float[] params,
			int params_offset) {
		gl.glGetVertexAttribfv(index, pname, params, params_offset);
	}

	public static void glInvalidateFramebuffer(int target, int numAttachments,
			int[] attachments, int attachments_offset) {
		gl.glInvalidateFramebuffer(target, numAttachments, attachments,
				attachments_offset);
	}

	public static void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
		gl.glGetBufferParameteriv(target, pname, params);
	}

	public static void glMultiDrawArrays(int mode, int[] first, int first_offset,
			int[] count, int count_offset, int drawcount) {
		gl.glMultiDrawArrays(mode, first, first_offset, count, count_offset,
				drawcount);
	}

	public static void glInvalidateSubFramebuffer(int target, int numAttachments,
			IntBuffer attachments, int x, int y, int width, int height) {
		gl.glInvalidateSubFramebuffer(target, numAttachments, attachments, x,
				y, width, height);
	}

	public static void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
		gl.glGetVertexAttribiv(index, pname, params);
	}

	public static void glMultiDrawArraysIndirectAMD(int mode, Buffer indirect,
			int primcount, int stride) {
		gl.glMultiDrawArraysIndirectAMD(mode, indirect, primcount, stride);
	}

	public static void glGetBufferParameteriv(int target, int pname, int[] params,
			int params_offset) {
		gl.glGetBufferParameteriv(target, pname, params, params_offset);
	}

	public static void glInvalidateSubFramebuffer(int target, int numAttachments,
			int[] attachments, int attachments_offset, int x, int y, int width,
			int height) {
		gl.glInvalidateSubFramebuffer(target, numAttachments, attachments,
				attachments_offset, x, y, width, height);
	}

	public static void glGetVertexAttribiv(int index, int pname, int[] params,
			int params_offset) {
		gl.glGetVertexAttribiv(index, pname, params, params_offset);
	}

	public static void glMultiDrawElements(int mode, IntBuffer count, int type,
			PointerBuffer indices, int drawcount) {
		gl.glMultiDrawElements(mode, count, type, indices, drawcount);
	}

	public static int glGetError() {
		return gl.glGetError();
	}

	public static void glGetFloatv(int pname, FloatBuffer data) {
		gl.glGetFloatv(pname, data);
	}

	public static boolean glIsProgram(int program) {
		return gl.glIsProgram(program);
	}

	public static boolean glIsTransformFeedback(int id) {
		return gl.glIsTransformFeedback(id);
	}

	public static void glMultiDrawElementsIndirectAMD(int mode, int type,
			Buffer indirect, int primcount, int stride) {
		gl.glMultiDrawElementsIndirectAMD(mode, type, indirect, primcount,
				stride);
	}

	public static void glGetFloatv(int pname, float[] data, int data_offset) {
		gl.glGetFloatv(pname, data, data_offset);
	}

	public static boolean glIsVertexArray(int array) {
		return gl.glIsVertexArray(array);
	}

	public static boolean glIsProgramPipeline(int pipeline) {
		return gl.glIsProgramPipeline(pipeline);
	}

	public static void glGetFramebufferAttachmentParameteriv(int target,
			int attachment, int pname, IntBuffer params) {
		gl.glGetFramebufferAttachmentParameteriv(target, attachment, pname,
				params);
	}

	public static void glNamedBufferPageCommitmentARB(int buffer, long offset,
			long size, boolean commit) {
		gl.glNamedBufferPageCommitmentARB(buffer, offset, size, commit);
	}

	public static boolean glIsQuery(int id) {
		return gl.glIsQuery(id);
	}

	public static void glMemoryBarrier(int barriers) {
		gl.glMemoryBarrier(barriers);
	}

	public static void glNamedBufferPageCommitmentEXT(int buffer, long offset,
			long size, boolean commit) {
		gl.glNamedBufferPageCommitmentEXT(buffer, offset, size, commit);
	}

	public static boolean glIsShader(int shader) {
		return gl.glIsShader(shader);
	}

	public static void glPauseTransformFeedback() {
		gl.glPauseTransformFeedback();
	}

	public static void glGetFramebufferAttachmentParameteriv(int target,
			int attachment, int pname, int[] params, int params_offset) {
		gl.glGetFramebufferAttachmentParameteriv(target, attachment, pname,
				params, params_offset);
	}

	public static void glNormalFormatNV(int type, int stride) {
		gl.glNormalFormatNV(type, stride);
	}

	public static void glLinkProgram(int program) {
		gl.glLinkProgram(program);
	}

	public static void glPixelStoref(int pname, float param) {
		gl.glPixelStoref(pname, param);
	}

	public static void glReadBuffer(int mode) {
		gl.glReadBuffer(mode);
	}

	public static void glObjectLabel(int identifier, int name, int length,
			ByteBuffer label) {
		gl.glObjectLabel(identifier, name, length, label);
	}

	public static void glPointParameterf(int pname, float param) {
		gl.glPointParameterf(pname, param);
	}

	public static void glResumeTransformFeedback() {
		gl.glResumeTransformFeedback();
	}

	public static int glGetGraphicsResetStatus() {
		return gl.glGetGraphicsResetStatus();
	}

	public static void glObjectLabel(int identifier, int name, int length,
			byte[] label, int label_offset) {
		gl.glObjectLabel(identifier, name, length, label, label_offset);
	}

	public static void glTexStorage2DMultisample(int target, int samples,
			int internalformat, int width, int height,
			boolean fixedsamplelocations) {
		gl.glTexStorage2DMultisample(target, samples, internalformat, width,
				height, fixedsamplelocations);
	}

	public static void glPointParameterfv(int pname, FloatBuffer params) {
		gl.glPointParameterfv(pname, params);
	}

	public static void glGetIntegerv(int pname, IntBuffer data) {
		gl.glGetIntegerv(pname, data);
	}

	public static void glObjectPtrLabel(Buffer ptr, int length, ByteBuffer label) {
		gl.glObjectPtrLabel(ptr, length, label);
	}

	public static void glGetIntegerv(int pname, int[] data, int data_offset) {
		gl.glGetIntegerv(pname, data, data_offset);
	}

	public static void glTransformFeedbackVaryings(int program, int count,
			String[] varyings, int bufferMode) {
		gl.glTransformFeedbackVaryings(program, count, varyings, bufferMode);
	}

	public static void glPointParameterfv(int pname, float[] params, int params_offset) {
		gl.glPointParameterfv(pname, params, params_offset);
	}

	public static void glGetRenderbufferParameteriv(int target, int pname,
			IntBuffer params) {
		gl.glGetRenderbufferParameteriv(target, pname, params);
	}

	public static void glObjectPtrLabel(Buffer ptr, int length, byte[] label,
			int label_offset) {
		gl.glObjectPtrLabel(ptr, length, label, label_offset);
	}

	public static void glUniform1ui(int location, int v0) {
		gl.glUniform1ui(location, v0);
	}

	public static void glPointParameteri(int pname, int param) {
		gl.glPointParameteri(pname, param);
	}

	public static void glPopDebugGroup() {
		gl.glPopDebugGroup();
	}

	public static void glUniform1uiv(int location, int count, IntBuffer value) {
		gl.glUniform1uiv(location, count, value);
	}

	public static void glGetRenderbufferParameteriv(int target, int pname,
			int[] params, int params_offset) {
		gl.glGetRenderbufferParameteriv(target, pname, params, params_offset);
	}

	public static void glPointParameteriv(int pname, IntBuffer params) {
		gl.glPointParameteriv(pname, params);
	}

	public static void glProgramBinary(int program, int binaryFormat, Buffer binary,
			int length) {
		gl.glProgramBinary(program, binaryFormat, binary, length);
	}

	public static void glUniform1uiv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform1uiv(location, count, value, value_offset);
	}

	public static void glPointParameteriv(int pname, int[] params, int params_offset) {
		gl.glPointParameteriv(pname, params, params_offset);
	}

	public static String glGetString(int name) {
		return gl.glGetString(name);
	}

	public static void glProgramParameteri(int program, int pname, int value) {
		gl.glProgramParameteri(program, pname, value);
	}

	public static void glUniform2ui(int location, int v0, int v1) {
		gl.glUniform2ui(location, v0, v1);
	}

	public static void glPointSize(float size) {
		gl.glPointSize(size);
	}

	public static void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
		gl.glGetTexParameterfv(target, pname, params);
	}

	public static void glPolygonMode(int face, int mode) {
		gl.glPolygonMode(face, mode);
	}

	public static void glUniform2uiv(int location, int count, IntBuffer value) {
		gl.glUniform2uiv(location, count, value);
	}

	public static void glGetTexParameterfv(int target, int pname, float[] params,
			int params_offset) {
		gl.glGetTexParameterfv(target, pname, params, params_offset);
	}

	public static void glPrimitiveRestartIndex(int index) {
		gl.glPrimitiveRestartIndex(index);
	}

	public static void glProgramUniform1f(int program, int location, float v0) {
		gl.glProgramUniform1f(program, location, v0);
	}

	public static void glProgramUniform1d(int program, int location, double v0) {
		gl.glProgramUniform1d(program, location, v0);
	}

	public static void glUniform2uiv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform2uiv(location, count, value, value_offset);
	}

	public static void glGetTexParameteriv(int target, int pname, IntBuffer params) {
		gl.glGetTexParameteriv(target, pname, params);
	}

	public static void glProgramUniform1fv(int program, int location, int count,
			FloatBuffer value) {
		gl.glProgramUniform1fv(program, location, count, value);
	}

	public static void glProgramUniform1dv(int program, int location, int count,
			DoubleBuffer value) {
		gl.glProgramUniform1dv(program, location, count, value);
	}

	public static void glUniform3ui(int location, int v0, int v1, int v2) {
		gl.glUniform3ui(location, v0, v1, v2);
	}

	public static void glGetTexParameteriv(int target, int pname, int[] params,
			int params_offset) {
		gl.glGetTexParameteriv(target, pname, params, params_offset);
	}

	public static void glProgramUniform1fv(int program, int location, int count,
			float[] value, int value_offset) {
		gl.glProgramUniform1fv(program, location, count, value, value_offset);
	}

	public static void glUniform3uiv(int location, int count, IntBuffer value) {
		gl.glUniform3uiv(location, count, value);
	}

	public static void glGetnUniformfv(int program, int location, int bufSize,
			FloatBuffer params) {
		gl.glGetnUniformfv(program, location, bufSize, params);
	}

	public static void glProgramUniform1dv(int program, int location, int count,
			double[] value, int value_offset) {
		gl.glProgramUniform1dv(program, location, count, value, value_offset);
	}

	public static void glUniform3uiv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform3uiv(location, count, value, value_offset);
	}

	public static void glProgramUniform1i(int program, int location, int v0) {
		gl.glProgramUniform1i(program, location, v0);
	}

	public static void glGetnUniformfv(int program, int location, int bufSize,
			float[] params, int params_offset) {
		gl.glGetnUniformfv(program, location, bufSize, params, params_offset);
	}

	public static void glProgramUniform2d(int program, int location, double v0,
			double v1) {
		gl.glProgramUniform2d(program, location, v0, v1);
	}

	public static void glUniform4ui(int location, int v0, int v1, int v2, int v3) {
		gl.glUniform4ui(location, v0, v1, v2, v3);
	}

	public static void glProgramUniform1iv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform1iv(program, location, count, value);
	}

	public static void glProgramUniform2dv(int program, int location, int count,
			DoubleBuffer value) {
		gl.glProgramUniform2dv(program, location, count, value);
	}

	public static void glGetnUniformiv(int program, int location, int bufSize,
			IntBuffer params) {
		gl.glGetnUniformiv(program, location, bufSize, params);
	}

	public static void glUniform4uiv(int location, int count, IntBuffer value) {
		gl.glUniform4uiv(location, count, value);
	}

	public static void glProgramUniform2dv(int program, int location, int count,
			double[] value, int value_offset) {
		gl.glProgramUniform2dv(program, location, count, value, value_offset);
	}

	public static void glProgramUniform1iv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform1iv(program, location, count, value, value_offset);
	}

	public static void glUniform4uiv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform4uiv(location, count, value, value_offset);
	}

	public static void glGetnUniformiv(int program, int location, int bufSize,
			int[] params, int params_offset) {
		gl.glGetnUniformiv(program, location, bufSize, params, params_offset);
	}

	public static void glProgramUniform3d(int program, int location, double v0,
			double v1, double v2) {
		gl.glProgramUniform3d(program, location, v0, v1, v2);
	}

	public static void glUniformBlockBinding(int program, int uniformBlockIndex,
			int uniformBlockBinding) {
		gl.glUniformBlockBinding(program, uniformBlockIndex,
				uniformBlockBinding);
	}

	public static void glProgramUniform1ui(int program, int location, int v0) {
		gl.glProgramUniform1ui(program, location, v0);
	}

	public static void glHint(int target, int mode) {
		gl.glHint(target, mode);
	}

	public static void glUniformMatrix2x3fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix2x3fv(location, count, transpose, value);
	}

	public static void glProgramUniform3dv(int program, int location, int count,
			DoubleBuffer value) {
		gl.glProgramUniform3dv(program, location, count, value);
	}

	public static boolean glIsBuffer(int buffer) {
		return gl.glIsBuffer(buffer);
	}

	public static void glProgramUniform1uiv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform1uiv(program, location, count, value);
	}

	public static boolean glIsEnabled(int cap) {
		return gl.glIsEnabled(cap);
	}

	public static void glUniformMatrix2x3fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix2x3fv(location, count, transpose, value, value_offset);
	}

	public static void glProgramUniform3dv(int program, int location, int count,
			double[] value, int value_offset) {
		gl.glProgramUniform3dv(program, location, count, value, value_offset);
	}

	public static boolean glIsFramebuffer(int framebuffer) {
		return gl.glIsFramebuffer(framebuffer);
	}

	public static void glProgramUniform1uiv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform1uiv(program, location, count, value, value_offset);
	}

	public static void glUniformMatrix2x4fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix2x4fv(location, count, transpose, value);
	}

	public static void glProgramUniform4d(int program, int location, double v0,
			double v1, double v2, double v3) {
		gl.glProgramUniform4d(program, location, v0, v1, v2, v3);
	}

	public static boolean glIsRenderbuffer(int renderbuffer) {
		return gl.glIsRenderbuffer(renderbuffer);
	}

	public static void glProgramUniform2f(int program, int location, float v0, float v1) {
		gl.glProgramUniform2f(program, location, v0, v1);
	}

	public static void glProgramUniform4dv(int program, int location, int count,
			DoubleBuffer value) {
		gl.glProgramUniform4dv(program, location, count, value);
	}

	public static void glUniformMatrix2x4fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix2x4fv(location, count, transpose, value, value_offset);
	}

	public static boolean glIsTexture(int texture) {
		return gl.glIsTexture(texture);
	}

	public static void glProgramUniform2fv(int program, int location, int count,
			FloatBuffer value) {
		gl.glProgramUniform2fv(program, location, count, value);
	}

	public static void glUniformMatrix3x2fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix3x2fv(location, count, transpose, value);
	}

	public static void glLineWidth(float width) {
		gl.glLineWidth(width);
	}

	public static void glProgramUniform4dv(int program, int location, int count,
			double[] value, int value_offset) {
		gl.glProgramUniform4dv(program, location, count, value, value_offset);
	}

	public static ByteBuffer glMapBuffer(int target, int access) {
		return gl.glMapBuffer(target, access);
	}

	public static void glProgramUniform2fv(int program, int location, int count,
			float[] value, int value_offset) {
		gl.glProgramUniform2fv(program, location, count, value, value_offset);
	}

	public static void glProgramUniformMatrix2dv(int program, int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix2dv(program, location, count, transpose, value);
	}

	public static void glUniformMatrix3x2fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix3x2fv(location, count, transpose, value, value_offset);
	}

	public static void glProgramUniform2i(int program, int location, int v0, int v1) {
		gl.glProgramUniform2i(program, location, v0, v1);
	}

	public static void glUniformMatrix3x4fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix3x4fv(location, count, transpose, value);
	}

	public static void glProgramUniformMatrix2dv(int program, int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix2dv(program, location, count, transpose,
				value, value_offset);
	}

	public static ByteBuffer glMapBufferRange(int target, long offset, long length,
			int access) {
		return gl.glMapBufferRange(target, offset, length, access);
	}

	public static void glProgramUniform2iv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform2iv(program, location, count, value);
	}

	public static void glUniformMatrix3x4fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix3x4fv(location, count, transpose, value, value_offset);
	}

	public static void glProgramUniformMatrix2x3dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix2x3dv(program, location, count, transpose,
				value);
	}

	public static void glUniformMatrix4x2fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix4x2fv(location, count, transpose, value);
	}

	public static void glProgramUniform2iv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform2iv(program, location, count, value, value_offset);
	}

	public static void glPixelStorei(int pname, int param) {
		gl.glPixelStorei(pname, param);
	}

	public static void glProgramUniformMatrix2x3dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix2x3dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glPolygonOffset(float factor, float units) {
		gl.glPolygonOffset(factor, units);
	}

	public static void glUniformMatrix4x2fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix4x2fv(location, count, transpose, value, value_offset);
	}

	public static void glProgramUniform2ui(int program, int location, int v0, int v1) {
		gl.glProgramUniform2ui(program, location, v0, v1);
	}

	public static void glProgramUniformMatrix2x4dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix2x4dv(program, location, count, transpose,
				value);
	}

	public static void glReadPixels(int x, int y, int width, int height, int format,
			int type, Buffer pixels) {
		gl.glReadPixels(x, y, width, height, format, type, pixels);
	}

	public static void glUniformMatrix4x3fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glUniformMatrix4x3fv(location, count, transpose, value);
	}

	public static void glProgramUniform2uiv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform2uiv(program, location, count, value);
	}

	public static void glReadPixels(int x, int y, int width, int height, int format,
			int type, long pixels_buffer_offset) {
		gl.glReadPixels(x, y, width, height, format, type, pixels_buffer_offset);
	}

	public static void glProgramUniformMatrix2x4dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix2x4dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glUniformMatrix4x3fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glUniformMatrix4x3fv(location, count, transpose, value, value_offset);
	}

	public static void glProgramUniform2uiv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform2uiv(program, location, count, value, value_offset);
	}

	public static void glReadnPixels(int x, int y, int width, int height, int format,
			int type, int bufSize, Buffer data) {
		gl.glReadnPixels(x, y, width, height, format, type, bufSize, data);
	}

	public static void glProgramUniformMatrix3dv(int program, int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix3dv(program, location, count, transpose, value);
	}

	public static void glVertexAttribDivisor(int index, int divisor) {
		gl.glVertexAttribDivisor(index, divisor);
	}

	public static void glProgramUniform3f(int program, int location, float v0,
			float v1, float v2) {
		gl.glProgramUniform3f(program, location, v0, v1, v2);
	}

	public static void glRenderbufferStorage(int target, int internalformat,
			int width, int height) {
		gl.glRenderbufferStorage(target, internalformat, width, height);
	}

	public static void glProgramUniformMatrix3dv(int program, int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix3dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttribI4i(int index, int x, int y, int z, int w) {
		gl.glVertexAttribI4i(index, x, y, z, w);
	}

	public static void glProgramUniform3fv(int program, int location, int count,
			FloatBuffer value) {
		gl.glProgramUniform3fv(program, location, count, value);
	}

	public static void glVertexAttribI4iv(int index, IntBuffer v) {
		gl.glVertexAttribI4iv(index, v);
	}

	public static void glRenderbufferStorageMultisample(int target, int samples,
			int internalformat, int width, int height) {
		gl.glRenderbufferStorageMultisample(target, samples, internalformat,
				width, height);
	}

	public static void glProgramUniformMatrix3x2dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix3x2dv(program, location, count, transpose,
				value);
	}

	public static void glVertexAttribI4iv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI4iv(index, v, v_offset);
	}

	public static void glProgramUniform3fv(int program, int location, int count,
			float[] value, int value_offset) {
		gl.glProgramUniform3fv(program, location, count, value, value_offset);
	}

	public static void glProgramUniformMatrix3x2dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix3x2dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
		gl.glVertexAttribI4ui(index, x, y, z, w);
	}

	public static void glProgramUniform3i(int program, int location, int v0, int v1,
			int v2) {
		gl.glProgramUniform3i(program, location, v0, v1, v2);
	}

	public static void glSampleCoverage(float value, boolean invert) {
		gl.glSampleCoverage(value, invert);
	}

	public static void glProgramUniformMatrix3x4dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix3x4dv(program, location, count, transpose,
				value);
	}

	public static void glVertexAttribI4uiv(int index, IntBuffer v) {
		gl.glVertexAttribI4uiv(index, v);
	}

	public static void glScissor(int x, int y, int width, int height) {
		gl.glScissor(x, y, width, height);
	}

	public static void glProgramUniform3iv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform3iv(program, location, count, value);
	}

	public static void glVertexAttribI4uiv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI4uiv(index, v, v_offset);
	}

	public static void glProgramUniformMatrix3x4dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix3x4dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glStencilFunc(int func, int ref, int mask) {
		gl.glStencilFunc(func, ref, mask);
	}

	public static void glVertexAttribIPointer(int index, int size, int type,
			int stride, long pointer_buffer_offset) {
		gl.glVertexAttribIPointer(index, size, type, stride,
				pointer_buffer_offset);
	}

	public static void glProgramUniform3iv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform3iv(program, location, count, value, value_offset);
	}

	public static void glStencilMask(int mask) {
		gl.glStencilMask(mask);
	}

	public static void glProgramUniformMatrix4dv(int program, int location, int count,
			boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix4dv(program, location, count, transpose, value);
	}

	public static void glStencilOp(int fail, int zfail, int zpass) {
		gl.glStencilOp(fail, zfail, zpass);
	}

	public static void glProgramUniform3ui(int program, int location, int v0, int v1,
			int v2) {
		gl.glProgramUniform3ui(program, location, v0, v1, v2);
	}

	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			Buffer pixels) {
		gl.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels);
	}

	public static boolean isPBOPackBound() {
		return gl.isPBOPackBound();
	}

	public static boolean isPBOUnpackBound() {
		return gl.isPBOUnpackBound();
	}

	public static void glProgramUniformMatrix4dv(int program, int location, int count,
			boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix4dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glProgramUniform3uiv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform3uiv(program, location, count, value);
	}

	public static void glTexImage2D(int target, int level, int internalformat,
			int width, int height, int border, int format, int type,
			long pixels_buffer_offset) {
		gl.glTexImage2D(target, level, internalformat, width, height, border,
				format, type, pixels_buffer_offset);
	}

	public static void glProgramUniformMatrix4x2dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix4x2dv(program, location, count, transpose,
				value);
	}

	public static void glProgramUniform3uiv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform3uiv(program, location, count, value, value_offset);
	}

	public static void glTexParameterf(int target, int pname, float param) {
		gl.glTexParameterf(target, pname, param);
	}

	public static void glProgramUniformMatrix4x2dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix4x2dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTexParameterfv(int target, int pname, FloatBuffer params) {
		gl.glTexParameterfv(target, pname, params);
	}

	public static void glProgramUniform4f(int program, int location, float v0,
			float v1, float v2, float v3) {
		gl.glProgramUniform4f(program, location, v0, v1, v2, v3);
	}

	public static void glTexParameterfv(int target, int pname, float[] params,
			int params_offset) {
		gl.glTexParameterfv(target, pname, params, params_offset);
	}

	public static void glProgramUniformMatrix4x3dv(int program, int location,
			int count, boolean transpose, DoubleBuffer value) {
		gl.glProgramUniformMatrix4x3dv(program, location, count, transpose,
				value);
	}

	public static void glProgramUniform4fv(int program, int location, int count,
			FloatBuffer value) {
		gl.glProgramUniform4fv(program, location, count, value);
	}

	public static void glTexParameteri(int target, int pname, int param) {
		gl.glTexParameteri(target, pname, param);
	}

	public static void glProgramUniformMatrix4x3dv(int program, int location,
			int count, boolean transpose, double[] value, int value_offset) {
		gl.glProgramUniformMatrix4x3dv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTexParameteriv(int target, int pname, IntBuffer params) {
		gl.glTexParameteriv(target, pname, params);
	}

	public static void glProgramUniform4fv(int program, int location, int count,
			float[] value, int value_offset) {
		gl.glProgramUniform4fv(program, location, count, value, value_offset);
	}

	public static void glTexParameteriv(int target, int pname, int[] params,
			int params_offset) {
		gl.glTexParameteriv(target, pname, params, params_offset);
	}

	public static void glProgramUniformui64NV(int program, int location, long value) {
		gl.glProgramUniformui64NV(program, location, value);
	}

	public static void glTexStorage1D(int target, int levels, int internalformat,
			int width) {
		gl.glTexStorage1D(target, levels, internalformat, width);
	}

	public static void glProgramUniformui64vNV(int program, int location, int count,
			LongBuffer value) {
		gl.glProgramUniformui64vNV(program, location, count, value);
	}

	public static void glProgramUniform4i(int program, int location, int v0, int v1,
			int v2, int v3) {
		gl.glProgramUniform4i(program, location, v0, v1, v2, v3);
	}

	public static void glTexStorage2D(int target, int levels, int internalformat,
			int width, int height) {
		gl.glTexStorage2D(target, levels, internalformat, width, height);
	}

	public static void glProgramUniformui64vNV(int program, int location, int count,
			long[] value, int value_offset) {
		gl.glProgramUniformui64vNV(program, location, count, value,
				value_offset);
	}

	public static void glProgramUniform4iv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform4iv(program, location, count, value);
	}

	public static void glProvokingVertex(int mode) {
		gl.glProvokingVertex(mode);
	}

	public static void glTexStorage3D(int target, int levels, int internalformat,
			int width, int height, int depth) {
		gl.glTexStorage3D(target, levels, internalformat, width, height, depth);
	}

	public static void glSecondaryColorFormatNV(int size, int type, int stride) {
		gl.glSecondaryColorFormatNV(size, type, stride);
	}

	public static void glProgramUniform4iv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform4iv(program, location, count, value, value_offset);
	}

	public static void glSetMultisamplefvAMD(int pname, int index, FloatBuffer val) {
		gl.glSetMultisamplefvAMD(pname, index, val);
	}

	public static void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			Buffer pixels) {
		gl.glTexSubImage2D(target, level, xoffset, yoffset, width, height,
				format, type, pixels);
	}

	public static void glProgramUniform4ui(int program, int location, int v0, int v1,
			int v2, int v3) {
		gl.glProgramUniform4ui(program, location, v0, v1, v2, v3);
	}

	public static void glSetMultisamplefvAMD(int pname, int index, float[] val,
			int val_offset) {
		gl.glSetMultisamplefvAMD(pname, index, val, val_offset);
	}

	public static void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			long pixels_buffer_offset) {
		gl.glTexSubImage2D(target, level, xoffset, yoffset, width, height,
				format, type, pixels_buffer_offset);
	}

	public static void glStencilOpValueAMD(int face, int value) {
		gl.glStencilOpValueAMD(face, value);
	}

	public static void glProgramUniform4uiv(int program, int location, int count,
			IntBuffer value) {
		gl.glProgramUniform4uiv(program, location, count, value);
	}

	public static void glTessellationFactorAMD(float factor) {
		gl.glTessellationFactorAMD(factor);
	}

	public static void glTessellationModeAMD(int mode) {
		gl.glTessellationModeAMD(mode);
	}

	public static void glTextureStorage1DEXT(int texture, int target, int levels,
			int internalformat, int width) {
		gl.glTextureStorage1DEXT(texture, target, levels, internalformat, width);
	}

	public static void glProgramUniform4uiv(int program, int location, int count,
			int[] value, int value_offset) {
		gl.glProgramUniform4uiv(program, location, count, value, value_offset);
	}

	public static void glTexBuffer(int target, int internalformat, int buffer) {
		gl.glTexBuffer(target, internalformat, buffer);
	}

	public static void glTextureStorage2DEXT(int texture, int target, int levels,
			int internalformat, int width, int height) {
		gl.glTextureStorage2DEXT(texture, target, levels, internalformat,
				width, height);
	}

	public static void glTexCoordFormatNV(int size, int type, int stride) {
		gl.glTexCoordFormatNV(size, type, stride);
	}

	public static void glProgramUniformMatrix2fv(int program, int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix2fv(program, location, count, transpose, value);
	}

	public static void glTextureStorage3DEXT(int texture, int target, int levels,
			int internalformat, int width, int height, int depth) {
		gl.glTextureStorage3DEXT(texture, target, levels, internalformat,
				width, height, depth);
	}

	public static void glTexImage1D(int target, int level, int internalFormat,
			int width, int border, int format, int type, Buffer pixels) {
		gl.glTexImage1D(target, level, internalFormat, width, border, format,
				type, pixels);
	}

	public static boolean glUnmapBuffer(int target) {
		return gl.glUnmapBuffer(target);
	}

	public static void glProgramUniformMatrix2fv(int program, int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix2fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTexImage1D(int target, int level, int internalFormat,
			int width, int border, int format, int type,
			long pixels_buffer_offset) {
		gl.glTexImage1D(target, level, internalFormat, width, border, format,
				type, pixels_buffer_offset);
	}

	public static void glViewport(int x, int y, int width, int height) {
		gl.glViewport(x, y, width, height);
	}

	public static void glTexImage2DMultisampleCoverageNV(int target,
			int coverageSamples, int colorSamples, int internalFormat,
			int width, int height, boolean fixedSampleLocations) {
		gl.glTexImage2DMultisampleCoverageNV(target, coverageSamples,
				colorSamples, internalFormat, width, height,
				fixedSampleLocations);
	}

	public static void glProgramUniformMatrix2x3fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix2x3fv(program, location, count, transpose,
				value);
	}

	public static void glTexImage3DMultisampleCoverageNV(int target,
			int coverageSamples, int colorSamples, int internalFormat,
			int width, int height, int depth, boolean fixedSampleLocations) {
		gl.glTexImage3DMultisampleCoverageNV(target, coverageSamples,
				colorSamples, internalFormat, width, height, depth,
				fixedSampleLocations);
	}

	public static void glProgramUniformMatrix2x3fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix2x3fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTexPageCommitmentARB(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			boolean resident) {
		gl.glTexPageCommitmentARB(target, level, xoffset, yoffset, zoffset,
				width, height, depth, resident);
	}

	public static void glProgramUniformMatrix2x4fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix2x4fv(program, location, count, transpose,
				value);
	}

	public static void glTexStorage3DMultisample(int target, int samples,
			int internalformat, int width, int height, int depth,
			boolean fixedsamplelocations) {
		gl.glTexStorage3DMultisample(target, samples, internalformat, width,
				height, depth, fixedsamplelocations);
	}

	public static void glProgramUniformMatrix2x4fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix2x4fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTexSubImage1D(int target, int level, int xoffset, int width,
			int format, int type, Buffer pixels) {
		gl.glTexSubImage1D(target, level, xoffset, width, format, type, pixels);
	}

	public static void glTexSubImage1D(int target, int level, int xoffset, int width,
			int format, int type, long pixels_buffer_offset) {
		gl.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels_buffer_offset);
	}

	public static void glProgramUniformMatrix3fv(int program, int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix3fv(program, location, count, transpose, value);
	}

	public static void glTextureImage2DMultisampleCoverageNV(int texture, int target,
			int coverageSamples, int colorSamples, int internalFormat,
			int width, int height, boolean fixedSampleLocations) {
		gl.glTextureImage2DMultisampleCoverageNV(texture, target,
				coverageSamples, colorSamples, internalFormat, width, height,
				fixedSampleLocations);
	}

	public static void glProgramUniformMatrix3fv(int program, int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix3fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTextureImage2DMultisampleNV(int texture, int target,
			int samples, int internalFormat, int width, int height,
			boolean fixedSampleLocations) {
		gl.glTextureImage2DMultisampleNV(texture, target, samples,
				internalFormat, width, height, fixedSampleLocations);
	}

	public static void glProgramUniformMatrix3x2fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix3x2fv(program, location, count, transpose,
				value);
	}

	public static void glTextureImage3DMultisampleCoverageNV(int texture, int target,
			int coverageSamples, int colorSamples, int internalFormat,
			int width, int height, int depth, boolean fixedSampleLocations) {
		gl.glTextureImage3DMultisampleCoverageNV(texture, target,
				coverageSamples, colorSamples, internalFormat, width, height,
				depth, fixedSampleLocations);
	}

	public static void glProgramUniformMatrix3x2fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix3x2fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glTextureImage3DMultisampleNV(int texture, int target,
			int samples, int internalFormat, int width, int height, int depth,
			boolean fixedSampleLocations) {
		gl.glTextureImage3DMultisampleNV(texture, target, samples,
				internalFormat, width, height, depth, fixedSampleLocations);
	}

	public static void glUniformui64NV(int location, long value) {
		gl.glUniformui64NV(location, value);
	}

	public static void glProgramUniformMatrix3x4fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix3x4fv(program, location, count, transpose,
				value);
	}

	public static void glUniformui64vNV(int location, int count, LongBuffer value) {
		gl.glUniformui64vNV(location, count, value);
	}

	public static void glUniformui64vNV(int location, int count, long[] value,
			int value_offset) {
		gl.glUniformui64vNV(location, count, value, value_offset);
	}

	public static void glProgramUniformMatrix3x4fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix3x4fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttrib1d(int index, double x) {
		gl.glVertexAttrib1d(index, x);
	}

	public static void glVertexAttrib1dv(int index, DoubleBuffer v) {
		gl.glVertexAttrib1dv(index, v);
	}

	public static void glProgramUniformMatrix4fv(int program, int location, int count,
			boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix4fv(program, location, count, transpose, value);
	}

	public static void glVertexAttrib1dv(int index, double[] v, int v_offset) {
		gl.glVertexAttrib1dv(index, v, v_offset);
	}

	public static void glProgramUniformMatrix4fv(int program, int location, int count,
			boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix4fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttrib1s(int index, short x) {
		gl.glVertexAttrib1s(index, x);
	}

	public static void glVertexAttrib1sv(int index, ShortBuffer v) {
		gl.glVertexAttrib1sv(index, v);
	}

	public static void glProgramUniformMatrix4x2fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix4x2fv(program, location, count, transpose,
				value);
	}

	public static void glVertexAttrib1sv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib1sv(index, v, v_offset);
	}

	public static void glProgramUniformMatrix4x2fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix4x2fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttrib2d(int index, double x, double y) {
		gl.glVertexAttrib2d(index, x, y);
	}

	public static void glVertexAttrib2dv(int index, DoubleBuffer v) {
		gl.glVertexAttrib2dv(index, v);
	}

	public static void glProgramUniformMatrix4x3fv(int program, int location,
			int count, boolean transpose, FloatBuffer value) {
		gl.glProgramUniformMatrix4x3fv(program, location, count, transpose,
				value);
	}

	public static void glVertexAttrib2dv(int index, double[] v, int v_offset) {
		gl.glVertexAttrib2dv(index, v, v_offset);
	}

	public static void glProgramUniformMatrix4x3fv(int program, int location,
			int count, boolean transpose, float[] value, int value_offset) {
		gl.glProgramUniformMatrix4x3fv(program, location, count, transpose,
				value, value_offset);
	}

	public static void glVertexAttrib2s(int index, short x, short y) {
		gl.glVertexAttrib2s(index, x, y);
	}

	public static void glVertexAttrib2sv(int index, ShortBuffer v) {
		gl.glVertexAttrib2sv(index, v);
	}

	public static void glPushDebugGroup(int source, int id, int length,
			ByteBuffer message) {
		gl.glPushDebugGroup(source, id, length, message);
	}

	public static void glVertexAttrib2sv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib2sv(index, v, v_offset);
	}

	public static void glPushDebugGroup(int source, int id, int length,
			byte[] message, int message_offset) {
		gl.glPushDebugGroup(source, id, length, message, message_offset);
	}

	public static void glVertexAttrib3d(int index, double x, double y, double z) {
		gl.glVertexAttrib3d(index, x, y, z);
	}

	public static void glQueryCounter(int id, int target) {
		gl.glQueryCounter(id, target);
	}

	public static void glVertexAttrib3dv(int index, DoubleBuffer v) {
		gl.glVertexAttrib3dv(index, v);
	}

	public static void glSampleMaski(int index, int mask) {
		gl.glSampleMaski(index, mask);
	}

	public static void glSamplerParameterIiv(int sampler, int pname, IntBuffer param) {
		gl.glSamplerParameterIiv(sampler, pname, param);
	}

	public static void glVertexAttrib3dv(int index, double[] v, int v_offset) {
		gl.glVertexAttrib3dv(index, v, v_offset);
	}

	public static void glVertexAttrib3s(int index, short x, short y, short z) {
		gl.glVertexAttrib3s(index, x, y, z);
	}

	public static void glSamplerParameterIiv(int sampler, int pname, int[] param,
			int param_offset) {
		gl.glSamplerParameterIiv(sampler, pname, param, param_offset);
	}

	public static void glVertexAttrib3sv(int index, ShortBuffer v) {
		gl.glVertexAttrib3sv(index, v);
	}

	public static void glSamplerParameterIuiv(int sampler, int pname, IntBuffer param) {
		gl.glSamplerParameterIuiv(sampler, pname, param);
	}

	public static void glVertexAttrib3sv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib3sv(index, v, v_offset);
	}

	public static void glSamplerParameterIuiv(int sampler, int pname, int[] param,
			int param_offset) {
		gl.glSamplerParameterIuiv(sampler, pname, param, param_offset);
	}

	public static void glVertexAttrib4Nbv(int index, ByteBuffer v) {
		gl.glVertexAttrib4Nbv(index, v);
	}

	public static void glShaderSource(int shader, int count, String[] string,
			IntBuffer length) {
		gl.glShaderSource(shader, count, string, length);
	}

	public static void glVertexAttrib4Nbv(int index, byte[] v, int v_offset) {
		gl.glVertexAttrib4Nbv(index, v, v_offset);
	}

	public static void glVertexAttrib4Niv(int index, IntBuffer v) {
		gl.glVertexAttrib4Niv(index, v);
	}

	public static void glShaderSource(int shader, int count, String[] string,
			int[] length, int length_offset) {
		gl.glShaderSource(shader, count, string, length, length_offset);
	}

	public static void glVertexAttrib4Niv(int index, int[] v, int v_offset) {
		gl.glVertexAttrib4Niv(index, v, v_offset);
	}

	public static void glStencilFuncSeparate(int face, int func, int ref, int mask) {
		gl.glStencilFuncSeparate(face, func, ref, mask);
	}

	public static void glVertexAttrib4Nsv(int index, ShortBuffer v) {
		gl.glVertexAttrib4Nsv(index, v);
	}

	public static void glStencilMaskSeparate(int face, int mask) {
		gl.glStencilMaskSeparate(face, mask);
	}

	public static void glVertexAttrib4Nsv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib4Nsv(index, v, v_offset);
	}

	public static void glStencilOpSeparate(int face, int sfail, int dpfail, int dppass) {
		gl.glStencilOpSeparate(face, sfail, dpfail, dppass);
	}

	public static void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
		gl.glVertexAttrib4Nub(index, x, y, z, w);
	}

	public static void glTexImage2DMultisample(int target, int samples,
			int internalformat, int width, int height,
			boolean fixedsamplelocations) {
		gl.glTexImage2DMultisample(target, samples, internalformat, width,
				height, fixedsamplelocations);
	}

	public static void glVertexAttrib4Nubv(int index, ByteBuffer v) {
		gl.glVertexAttrib4Nubv(index, v);
	}

	public static void glTexImage3D(int target, int level, int internalformat,
			int width, int height, int depth, int border, int format, int type,
			Buffer pixels) {
		gl.glTexImage3D(target, level, internalformat, width, height, depth,
				border, format, type, pixels);
	}

	public static void glVertexAttrib4Nubv(int index, byte[] v, int v_offset) {
		gl.glVertexAttrib4Nubv(index, v, v_offset);
	}

	public static void glVertexAttrib4Nuiv(int index, IntBuffer v) {
		gl.glVertexAttrib4Nuiv(index, v);
	}

	public static void glTexImage3D(int target, int level, int internalformat,
			int width, int height, int depth, int border, int format, int type,
			long pixels_buffer_offset) {
		gl.glTexImage3D(target, level, internalformat, width, height, depth,
				border, format, type, pixels_buffer_offset);
	}

	public static void glVertexAttrib4Nuiv(int index, int[] v, int v_offset) {
		gl.glVertexAttrib4Nuiv(index, v, v_offset);
	}

	public static void glVertexAttrib4Nusv(int index, ShortBuffer v) {
		gl.glVertexAttrib4Nusv(index, v);
	}

	public static void glTexImage3DMultisample(int target, int samples,
			int internalformat, int width, int height, int depth,
			boolean fixedsamplelocations) {
		gl.glTexImage3DMultisample(target, samples, internalformat, width,
				height, depth, fixedsamplelocations);
	}

	public static void glVertexAttrib4Nusv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib4Nusv(index, v, v_offset);
	}

	public static void glTexParameterIiv(int target, int pname, IntBuffer params) {
		gl.glTexParameterIiv(target, pname, params);
	}

	public static void glVertexAttrib4bv(int index, ByteBuffer v) {
		gl.glVertexAttrib4bv(index, v);
	}

	public static void glVertexAttrib4bv(int index, byte[] v, int v_offset) {
		gl.glVertexAttrib4bv(index, v, v_offset);
	}

	public static void glTexParameterIiv(int target, int pname, int[] params,
			int params_offset) {
		gl.glTexParameterIiv(target, pname, params, params_offset);
	}

	public static void glVertexAttrib4d(int index, double x, double y, double z,
			double w) {
		gl.glVertexAttrib4d(index, x, y, z, w);
	}

	public static void glTexParameterIuiv(int target, int pname, IntBuffer params) {
		gl.glTexParameterIuiv(target, pname, params);
	}

	public static void glVertexAttrib4dv(int index, DoubleBuffer v) {
		gl.glVertexAttrib4dv(index, v);
	}

	public static void glTexParameterIuiv(int target, int pname, int[] params,
			int params_offset) {
		gl.glTexParameterIuiv(target, pname, params, params_offset);
	}

	public static void glVertexAttrib4dv(int index, double[] v, int v_offset) {
		gl.glVertexAttrib4dv(index, v, v_offset);
	}

	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, Buffer pixels) {
		gl.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels);
	}

	public static void glVertexAttrib4iv(int index, IntBuffer v) {
		gl.glVertexAttrib4iv(index, v);
	}

	public static void glVertexAttrib4iv(int index, int[] v, int v_offset) {
		gl.glVertexAttrib4iv(index, v, v_offset);
	}

	public static void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, long pixels_buffer_offset) {
		gl.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width,
				height, depth, format, type, pixels_buffer_offset);
	}

	public static void glVertexAttrib4s(int index, short x, short y, short z, short w) {
		gl.glVertexAttrib4s(index, x, y, z, w);
	}

	public static void glVertexAttrib4sv(int index, ShortBuffer v) {
		gl.glVertexAttrib4sv(index, v);
	}

	public static void glUniform1f(int location, float v0) {
		gl.glUniform1f(location, v0);
	}

	public static void glVertexAttrib4sv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib4sv(index, v, v_offset);
	}

	public static void glUniform1fv(int location, int count, FloatBuffer value) {
		gl.glUniform1fv(location, count, value);
	}

	public static void glVertexAttrib4ubv(int index, ByteBuffer v) {
		gl.glVertexAttrib4ubv(index, v);
	}

	public static void glUniform1fv(int location, int count, float[] value,
			int value_offset) {
		gl.glUniform1fv(location, count, value, value_offset);
	}

	public static void glVertexAttrib4ubv(int index, byte[] v, int v_offset) {
		gl.glVertexAttrib4ubv(index, v, v_offset);
	}

	public static void glUniform1i(int location, int v0) {
		gl.glUniform1i(location, v0);
	}

	public static void glUniform1iv(int location, int count, IntBuffer value) {
		gl.glUniform1iv(location, count, value);
	}

	public static void glVertexAttrib4uiv(int index, IntBuffer v) {
		gl.glVertexAttrib4uiv(index, v);
	}

	public static void glVertexAttrib4uiv(int index, int[] v, int v_offset) {
		gl.glVertexAttrib4uiv(index, v, v_offset);
	}

	public static void glUniform1iv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform1iv(location, count, value, value_offset);
	}

	public static void glVertexAttrib4usv(int index, ShortBuffer v) {
		gl.glVertexAttrib4usv(index, v);
	}

	public static void glUniform2f(int location, float v0, float v1) {
		gl.glUniform2f(location, v0, v1);
	}

	public static void glVertexAttrib4usv(int index, short[] v, int v_offset) {
		gl.glVertexAttrib4usv(index, v, v_offset);
	}

	public static void glUniform2fv(int location, int count, FloatBuffer value) {
		gl.glUniform2fv(location, count, value);
	}

	public static void glVertexAttribFormatNV(int index, int size, int type,
			boolean normalized, int stride) {
		gl.glVertexAttribFormatNV(index, size, type, normalized, stride);
	}

	public static void glUniform2fv(int location, int count, float[] value,
			int value_offset) {
		gl.glUniform2fv(location, count, value, value_offset);
	}

	public static void glVertexAttribI1i(int index, int x) {
		gl.glVertexAttribI1i(index, x);
	}

	public static void glUniform2i(int location, int v0, int v1) {
		gl.glUniform2i(location, v0, v1);
	}

	public static void glVertexAttribI1iv(int index, IntBuffer v) {
		gl.glVertexAttribI1iv(index, v);
	}

	public static void glUniform2iv(int location, int count, IntBuffer value) {
		gl.glUniform2iv(location, count, value);
	}

	public static void glVertexAttribI1iv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI1iv(index, v, v_offset);
	}

	public static void glUniform2iv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform2iv(location, count, value, value_offset);
	}

	public static void glVertexAttribI1ui(int index, int x) {
		gl.glVertexAttribI1ui(index, x);
	}

	public static void glVertexAttribI1uiv(int index, IntBuffer v) {
		gl.glVertexAttribI1uiv(index, v);
	}

	public static void glUniform3f(int location, float v0, float v1, float v2) {
		gl.glUniform3f(location, v0, v1, v2);
	}

	public static void glVertexAttribI1uiv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI1uiv(index, v, v_offset);
	}

	public static void glUniform3fv(int location, int count, FloatBuffer value) {
		gl.glUniform3fv(location, count, value);
	}

	public static void glVertexAttribI2i(int index, int x, int y) {
		gl.glVertexAttribI2i(index, x, y);
	}

	public static void glUniform3fv(int location, int count, float[] value,
			int value_offset) {
		gl.glUniform3fv(location, count, value, value_offset);
	}

	public static void glVertexAttribI2iv(int index, IntBuffer v) {
		gl.glVertexAttribI2iv(index, v);
	}

	public static void glUniform3i(int location, int v0, int v1, int v2) {
		gl.glUniform3i(location, v0, v1, v2);
	}

	public static void glVertexAttribI2iv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI2iv(index, v, v_offset);
	}

	public static void glUniform3iv(int location, int count, IntBuffer value) {
		gl.glUniform3iv(location, count, value);
	}

	public static void glVertexAttribI2ui(int index, int x, int y) {
		gl.glVertexAttribI2ui(index, x, y);
	}

	public static void glVertexAttribI2uiv(int index, IntBuffer v) {
		gl.glVertexAttribI2uiv(index, v);
	}

	public static void glUniform3iv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform3iv(location, count, value, value_offset);
	}

	public static void glVertexAttribI2uiv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI2uiv(index, v, v_offset);
	}

	public static void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		gl.glUniform4f(location, v0, v1, v2, v3);
	}

	public static void glVertexAttribI3i(int index, int x, int y, int z) {
		gl.glVertexAttribI3i(index, x, y, z);
	}

	public static void glUniform4fv(int location, int count, FloatBuffer value) {
		gl.glUniform4fv(location, count, value);
	}

	public static void glVertexAttribI3iv(int index, IntBuffer v) {
		gl.glVertexAttribI3iv(index, v);
	}

	public static void glUniform4fv(int location, int count, float[] value,
			int value_offset) {
		gl.glUniform4fv(location, count, value, value_offset);
	}

	public static void glVertexAttribI3iv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI3iv(index, v, v_offset);
	}

	public static void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		gl.glUniform4i(location, v0, v1, v2, v3);
	}

	public static void glVertexAttribI3ui(int index, int x, int y, int z) {
		gl.glVertexAttribI3ui(index, x, y, z);
	}

	public static void glUniform4iv(int location, int count, IntBuffer value) {
		gl.glUniform4iv(location, count, value);
	}

	public static void glVertexAttribI3uiv(int index, IntBuffer v) {
		gl.glVertexAttribI3uiv(index, v);
	}

	public static void glUniform4iv(int location, int count, int[] value,
			int value_offset) {
		gl.glUniform4iv(location, count, value, value_offset);
	}

	public static void glVertexAttribI3uiv(int index, int[] v, int v_offset) {
		gl.glVertexAttribI3uiv(index, v, v_offset);
	}

	public static void glVertexAttribI4bv(int index, ByteBuffer v) {
		gl.glVertexAttribI4bv(index, v);
	}

	public static void glUniformMatrix2fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		gl.glUniformMatrix2fv(location, count, transpose, value);
	}

	public static void glVertexAttribI4bv(int index, byte[] v, int v_offset) {
		gl.glVertexAttribI4bv(index, v, v_offset);
	}

	public static void glUniformMatrix2fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		gl.glUniformMatrix2fv(location, count, transpose, value, value_offset);
	}

	public static void glVertexAttribI4sv(int index, ShortBuffer v) {
		gl.glVertexAttribI4sv(index, v);
	}

	public static void glUniformMatrix3fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		gl.glUniformMatrix3fv(location, count, transpose, value);
	}

	public static void glVertexAttribI4sv(int index, short[] v, int v_offset) {
		gl.glVertexAttribI4sv(index, v, v_offset);
	}

	public static void glVertexAttribI4ubv(int index, ByteBuffer v) {
		gl.glVertexAttribI4ubv(index, v);
	}

	public static void glUniformMatrix3fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		gl.glUniformMatrix3fv(location, count, transpose, value, value_offset);
	}

	public static void glVertexAttribI4ubv(int index, byte[] v, int v_offset) {
		gl.glVertexAttribI4ubv(index, v, v_offset);
	}

	public static void glUniformMatrix4fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		gl.glUniformMatrix4fv(location, count, transpose, value);
	}

	public static void glVertexAttribI4usv(int index, ShortBuffer v) {
		gl.glVertexAttribI4usv(index, v);
	}

	public static void glUniformMatrix4fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		gl.glUniformMatrix4fv(location, count, transpose, value, value_offset);
	}

	public static void glVertexAttribI4usv(int index, short[] v, int v_offset) {
		gl.glVertexAttribI4usv(index, v, v_offset);
	}

	public static void glUseProgram(int program) {
		gl.glUseProgram(program);
	}

	public static void glVertexAttribIFormatNV(int index, int size, int type,
			int stride) {
		gl.glVertexAttribIFormatNV(index, size, type, stride);
	}

	public static void glUseProgramStages(int pipeline, int stages, int program) {
		gl.glUseProgramStages(pipeline, stages, program);
	}

	public static void glVertexAttribL1d(int index, double x) {
		gl.glVertexAttribL1d(index, x);
	}

	public static void glValidateProgram(int program) {
		gl.glValidateProgram(program);
	}

	public static void glVertexAttribL1dv(int index, DoubleBuffer v) {
		gl.glVertexAttribL1dv(index, v);
	}

	public static void glValidateProgramPipeline(int pipeline) {
		gl.glValidateProgramPipeline(pipeline);
	}

	public static void glVertexAttribL1dv(int index, double[] v, int v_offset) {
		gl.glVertexAttribL1dv(index, v, v_offset);
	}

	public static void glVertexAttrib1f(int index, float x) {
		gl.glVertexAttrib1f(index, x);
	}

	public static void glVertexAttribL2d(int index, double x, double y) {
		gl.glVertexAttribL2d(index, x, y);
	}

	public static void glVertexAttrib1fv(int index, FloatBuffer v) {
		gl.glVertexAttrib1fv(index, v);
	}

	public static void glVertexAttribL2dv(int index, DoubleBuffer v) {
		gl.glVertexAttribL2dv(index, v);
	}

	public static void glVertexAttrib1fv(int index, float[] v, int v_offset) {
		gl.glVertexAttrib1fv(index, v, v_offset);
	}

	public static void glVertexAttribL2dv(int index, double[] v, int v_offset) {
		gl.glVertexAttribL2dv(index, v, v_offset);
	}

	public static void glVertexAttribL3d(int index, double x, double y, double z) {
		gl.glVertexAttribL3d(index, x, y, z);
	}

	public static void glVertexAttrib2f(int index, float x, float y) {
		gl.glVertexAttrib2f(index, x, y);
	}

	public static void glVertexAttribL3dv(int index, DoubleBuffer v) {
		gl.glVertexAttribL3dv(index, v);
	}

	public static void glVertexAttrib2fv(int index, FloatBuffer v) {
		gl.glVertexAttrib2fv(index, v);
	}

	public static void glVertexAttribL3dv(int index, double[] v, int v_offset) {
		gl.glVertexAttribL3dv(index, v, v_offset);
	}

	public static void glVertexAttrib2fv(int index, float[] v, int v_offset) {
		gl.glVertexAttrib2fv(index, v, v_offset);
	}

	public static void glVertexAttribL4d(int index, double x, double y, double z,
			double w) {
		gl.glVertexAttribL4d(index, x, y, z, w);
	}

	public static void glVertexAttrib3f(int index, float x, float y, float z) {
		gl.glVertexAttrib3f(index, x, y, z);
	}

	public static void glVertexAttribL4dv(int index, DoubleBuffer v) {
		gl.glVertexAttribL4dv(index, v);
	}

	public static void glVertexAttrib3fv(int index, FloatBuffer v) {
		gl.glVertexAttrib3fv(index, v);
	}

	public static void glVertexAttribL4dv(int index, double[] v, int v_offset) {
		gl.glVertexAttribL4dv(index, v, v_offset);
	}

	public static void glVertexAttrib3fv(int index, float[] v, int v_offset) {
		gl.glVertexAttrib3fv(index, v, v_offset);
	}

	public static void glVertexAttribLPointer(int index, int size, int type,
			int stride, long pointer_buffer_offset) {
		gl.glVertexAttribLPointer(index, size, type, stride,
				pointer_buffer_offset);
	}

	public static void glVertexAttrib4f(int index, float x, float y, float z, float w) {
		gl.glVertexAttrib4f(index, x, y, z, w);
	}

	public static void glVertexFormatNV(int size, int type, int stride) {
		gl.glVertexFormatNV(size, type, stride);
	}

	public static void glVertexAttrib4fv(int index, FloatBuffer v) {
		gl.glVertexAttrib4fv(index, v);
	}

	public static void glVertexAttrib4fv(int index, float[] v, int v_offset) {
		gl.glVertexAttrib4fv(index, v, v_offset);
	}

	public static void glVertexAttribPointer(int index, int size, int type,
			boolean normalized, int stride, long pointer_buffer_offset) {
		gl.glVertexAttribPointer(index, size, type, normalized, stride,
				pointer_buffer_offset);
	}

	public static void glReleaseShaderCompiler() {
		gl.glReleaseShaderCompiler();
	}

	public static void glShaderBinary(int n, IntBuffer shaders, int binaryformat,
			Buffer binary, int length) {
		gl.glShaderBinary(n, shaders, binaryformat, binary, length);
	}

	public static void glShaderBinary(int n, int[] shaders, int shaders_offset,
			int binaryformat, Buffer binary, int length) {
		gl.glShaderBinary(n, shaders, shaders_offset, binaryformat, binary,
				length);
	}

	public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype,
			IntBuffer range, IntBuffer precision) {
		gl.glGetShaderPrecisionFormat(shadertype, precisiontype, range,
				precision);
	}

	public static void glGetShaderPrecisionFormat(int shadertype, int precisiontype,
			int[] range, int range_offset, int[] precision, int precision_offset) {
		gl.glGetShaderPrecisionFormat(shadertype, precisiontype, range,
				range_offset, precision, precision_offset);
	}

	public static void glDepthRangef(float zNear, float zFar) {
		gl.glDepthRangef(zNear, zFar);
	}

	public static void glDepthRange(double zNear, double zFar) {
		gl.glDepthRange(zNear, zFar);
	}

	public static void glClearDepthf(float depth) {
		gl.glClearDepthf(depth);
	}

	public static void glClearDepth(double depth) {
		gl.glClearDepth(depth);
	}

	public static void glVertexAttribPointer(GLArrayData array) {
		gl.glVertexAttribPointer(array);
	}

	public static void glUniform(GLUniformData data) {
		gl.glUniform(data);
	}
	
	
}
