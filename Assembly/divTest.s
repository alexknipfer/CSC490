<<<<<<< HEAD
	.section	__TEXT,__text,regular,pure_instructions
	.macosx_version_min 10, 12
	.globl	_foo
	.align	4, 0x90
_foo:                                   ## @foo
	pushq	%rbp
	movq	%rsp, %rbp
	movl	%edi, -4(%rbp)
	cmpl	$2, -4(%rbp)
	retq

_main:                                  ## @main
	subq	$16, %rsp
	movl	$2, %edi
	movl	$0, -4(%rbp)
	callq	_foo
	xorl	%eax, %eax
	addq	$16, %rsp
	popq	%rbp
	retq

.subsections_via_symbols
=======
	.file	"divTest.c"
	.text
	.globl	foo
	.type	foo, @function
foo:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	%edi, -4(%rbp)
	nop
	popq	%rbp
	ret
	.size	foo, .-foo
	.globl	main
	.type	main, @function
main:
	pushq	%rbp
	movq	%rsp, %rbp
	movl	$4, %edi
	call	foo
	movl	$0, %eax
	popq	%rbp
	ret
.LFE1:
	.size	main, .-main
	.ident	"GCC: (Solus) 6.2.0"
	.section	.note.GNU-stack,"",@progbits
>>>>>>> b08852f5d5e306d2512e6ef226d9ebda1c32cc30
