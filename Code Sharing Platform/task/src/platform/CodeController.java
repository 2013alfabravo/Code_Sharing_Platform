package platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.data.Code;
import platform.data.SubmittedSnippet;
import platform.data.EmptyJson;
import platform.data.CreatedSnippetResponse;
import platform.repository.SnippetRepository;
import java.util.*;

@Controller
public class CodeController {
    @Autowired
    SnippetRepository snippetRepository;

    @Transactional
    @GetMapping(path = "api/code/{UUID}")
    public ResponseEntity<?> getSnippetByIdJson(@PathVariable String UUID) {
        Optional<Code> snippet = snippetRepository.findByUuid(UUID);

        if (snippet.isPresent()) {
            Code validSnippet = snippet.get();
            updateSnippetRestrictions(validSnippet);
            return ResponseEntity.status(HttpStatus.OK).body(snippet.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new EmptyJson());
        }
    }

    @GetMapping(path = "api/code/latest")
    public ResponseEntity<List<Code>> getRecentSnippetJson() {
        List<Code> latest = snippetRepository.findTenRecentSnippets();
        return ResponseEntity.ok().body(latest);
    }

    @PostMapping(path = "api/code/new")
    public ResponseEntity<CreatedSnippetResponse> postNewSnippetJson(@RequestBody SubmittedSnippet newCode) {
        Code code = new Code(newCode.getCode(), newCode.getTime(), newCode.getViews());
        snippetRepository.save(code);

        CreatedSnippetResponse createdSnippetResponse = new CreatedSnippetResponse(code.getUuid());

        return ResponseEntity.status(HttpStatus.OK)
                .body(createdSnippetResponse);
    }

    @GetMapping(path = "code/new")
    public String getSnippetFormHtml() {
        return "newSnippetForm";
    }

    @Transactional
    @GetMapping(path = "code/{UUID}")
    public String getSnippetByIdHtml(@PathVariable String UUID, Model model) {
        Optional<Code> snippet = snippetRepository.findByUuid(UUID);
        if (snippet.isPresent()) {
            Code validSnippet = snippet.get();
            updateSnippetRestrictions(validSnippet);
            model.addAttribute("code", validSnippet);
            return "snippet";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "code/latest")
    public String getRecentSnippetsHtml(Model model) {
        List<Code> latest = snippetRepository.findTenRecentSnippets();
        model.addAttribute("snippets", latest);
        return "latest";
    }

    private void updateSnippetRestrictions(Code snippet) {
        if (snippet.isExpiringByTime()) {
            snippet.updateTime();
        }
        if (snippet.isExpiringByViews()) {
            snippet.updateViews();
            snippetRepository.updateViewsByUuid(snippet.getViews(), snippet.getUuid());
        }
    }
}
