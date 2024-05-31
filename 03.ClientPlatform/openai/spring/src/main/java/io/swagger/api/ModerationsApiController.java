package io.swagger.api;

import io.swagger.model.CreateModerationRequest;
import io.swagger.model.CreateModerationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-05-31T08:43:25.403038835+09:00[Asia/Tokyo]")
@RestController
public class ModerationsApiController implements ModerationsApi {

    private static final Logger log = LoggerFactory.getLogger(ModerationsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ModerationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CreateModerationResponse> createModeration(@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody CreateModerationRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<CreateModerationResponse>(objectMapper.readValue("{\n  \"model\" : \"model\",\n  \"id\" : \"id\",\n  \"results\" : [ {\n    \"category_scores\" : {\n      \"self-harm/intent\" : 2.3021358869347655,\n      \"hate/threatening\" : 6.027456183070403,\n      \"self-harm/instructions\" : 7.061401241503109,\n      \"sexual/minors\" : 3.616076749251911,\n      \"harassment/threatening\" : 5.962133916683182,\n      \"hate\" : 0.8008281904610115,\n      \"self-harm\" : 5.637376656633329,\n      \"harassment\" : 1.4658129805029452,\n      \"sexual\" : 9.301444243932576,\n      \"violence/graphic\" : 4.145608029883936,\n      \"violence\" : 2.027123023002322\n    },\n    \"flagged\" : true,\n    \"categories\" : {\n      \"self-harm/intent\" : true,\n      \"hate/threatening\" : true,\n      \"self-harm/instructions\" : true,\n      \"sexual/minors\" : true,\n      \"harassment/threatening\" : true,\n      \"hate\" : true,\n      \"self-harm\" : true,\n      \"harassment\" : true,\n      \"sexual\" : true,\n      \"violence/graphic\" : true,\n      \"violence\" : true\n    }\n  }, {\n    \"category_scores\" : {\n      \"self-harm/intent\" : 2.3021358869347655,\n      \"hate/threatening\" : 6.027456183070403,\n      \"self-harm/instructions\" : 7.061401241503109,\n      \"sexual/minors\" : 3.616076749251911,\n      \"harassment/threatening\" : 5.962133916683182,\n      \"hate\" : 0.8008281904610115,\n      \"self-harm\" : 5.637376656633329,\n      \"harassment\" : 1.4658129805029452,\n      \"sexual\" : 9.301444243932576,\n      \"violence/graphic\" : 4.145608029883936,\n      \"violence\" : 2.027123023002322\n    },\n    \"flagged\" : true,\n    \"categories\" : {\n      \"self-harm/intent\" : true,\n      \"hate/threatening\" : true,\n      \"self-harm/instructions\" : true,\n      \"sexual/minors\" : true,\n      \"harassment/threatening\" : true,\n      \"hate\" : true,\n      \"self-harm\" : true,\n      \"harassment\" : true,\n      \"sexual\" : true,\n      \"violence/graphic\" : true,\n      \"violence\" : true\n    }\n  } ]\n}", CreateModerationResponse.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<CreateModerationResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<CreateModerationResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
