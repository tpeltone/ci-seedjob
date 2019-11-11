def foldername = 'ci-as-code'
def folderDisplayName = 'ci-as-code'

// Create folder
folder(foldername) {
    displayName(folderDisplayName)
    description('Folder for Jenkins Continuous Integration As Code')
}