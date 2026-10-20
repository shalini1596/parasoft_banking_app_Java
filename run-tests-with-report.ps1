param(
    [string[]] $MavenArgs = @()
)

$ErrorActionPreference = "Stop"

Push-Location $PSScriptRoot
try {
    & mvn clean test @MavenArgs
    $testExitCode = $LASTEXITCODE

    & mvn allure:serve
    $reportExitCode = $LASTEXITCODE
}
finally {
    Pop-Location
}

if ($reportExitCode -ne 0) {
    Write-Error "Allure report generation failed with exit code $reportExitCode."
}

if ($testExitCode -ne 0) {
    Write-Host "Tests completed with failures. The Allure report contains the scenario statuses."
    exit $testExitCode
}
