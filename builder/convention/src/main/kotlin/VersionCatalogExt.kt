import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionConstraint
import org.gradle.api.provider.Provider

fun VersionCatalog.getLib(
    alias: String,
): Provider<MinimalExternalModuleDependency> = findLibrary(alias).get()

fun VersionCatalog.getVersion(
    alias: String,
): VersionConstraint = findVersion(alias).get()
