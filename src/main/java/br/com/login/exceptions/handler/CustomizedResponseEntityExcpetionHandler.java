package br.com.login.exceptions.handler;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.login.exceptions.InvalidDataException;
import br.com.login.exceptions.InvalidOperationException;
import br.com.login.exceptions.ResourceNotFoundException;
import br.com.login.exceptions.StandardError;
import br.com.login.exceptions.StandardErrorValidations;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExcpetionHandler extends ResponseEntityExceptionHandler
		implements AccessDeniedHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String error = "Invalid Argument";
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());
		StandardErrorValidations err = new StandardErrorValidations(Instant.now(), status.value(), error, errorList,
				((ServletWebRequest) request).getRequest().getRequestURI().toString());

		return ResponseEntity.status(status).body(err);
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		String route = "/acess-denied" + request.getRequestURI();
		System.out.println(route);
		response.sendRedirect(route);
	}

//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<StandardError> handleAllExceptions(Exception e, HttpServletRequest request) {
//
//		String error = "General error, see log for more details";
//		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
//		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
//				request.getRequestURI());
//
//		return ResponseEntity.status(status).body(err);
//	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> sqlIntegrityViolation(DataIntegrityViolationException e,
			HttpServletRequest request) {

		String error = "Error in inserted data, duplicated value";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String errors = e.getMostSpecificCause().getLocalizedMessage().substring(
				e.getMostSpecificCause().getLocalizedMessage().indexOf("Detail") + 8,
				e.getMostSpecificCause().getLocalizedMessage().length());

		StandardError err = new StandardError(Instant.now(), status.value(), error, errors, request.getRequestURI());

		return ResponseEntity.status(status).body(err);

	}

	@ExceptionHandler(InvalidOperationException.class)
	public ResponseEntity<StandardError> invalidOperation(InvalidOperationException e, HttpServletRequest request) {

		String error = "One or more register associated";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<StandardError> userDisabled(DisabledException e, HttpServletRequest request) {

		String error = "Username or password incorrect";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, "Username or password incorrect",
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<StandardError> badCredentials(BadCredentialsException e, HttpServletRequest request) {

		String error = "Username or password incorrect";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<StandardError> invalidData(InvalidDataException e, HttpServletRequest request) {

		String error = "Invalid operation, this user are not the same authenticated";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}
}