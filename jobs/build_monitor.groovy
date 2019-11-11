// Create Build Monitor View
buildMonitorView("Zippleback-build-status") {
    description('Jobs for project Zippleback, master only')
    jobs {
        regex(/(^.*master.*$)/)
    }

    recurse(true)

    configure { project ->
	    (project / columns ).value = 2
    }

    configure { project ->
	    (project / config / displayCommitters ).value = true
	    (project / config / buildFailureAnalyzerDisplayedField ).value = Name
    }
}